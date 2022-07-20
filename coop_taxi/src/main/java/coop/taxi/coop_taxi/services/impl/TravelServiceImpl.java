package coop.taxi.coop_taxi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;
import coop.taxi.coop_taxi.exceptions.NoContentException;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Client;
import coop.taxi.coop_taxi.models.Driver;
import coop.taxi.coop_taxi.models.Travel;
import coop.taxi.coop_taxi.repositories.ClientRepository;
import coop.taxi.coop_taxi.repositories.DriverRepository;
import coop.taxi.coop_taxi.repositories.TravelRepository;
import coop.taxi.coop_taxi.services.TravelService;

@Service
public class TravelServiceImpl implements TravelService{
    final ModelMapper modelMapper;
    final TravelRepository repository;
    final DriverRepository driverRepository;
    final ClientRepository clientRepository;

    public TravelServiceImpl(TravelRepository r, DriverRepository dr, ClientRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.clientRepository = er;
        this.driverRepository = dr;
    }

    @Override
    @Transactional
    public List<TravelDTO> create(Long idClient, Long idDriver, List<NewTravelDTO> travels) {
        Client client = clientRepository.findById(idClient).orElseThrow(()-> new ResourceNotFoundException("Client not found"));
        Driver driver = driverRepository.findById(idDriver).orElseThrow(()-> new ResourceNotFoundException("Driver not found"));
        driver.setClient(client);
        List<TravelDTO> result = new ArrayList<TravelDTO>();
        for(NewTravelDTO tr : travels){
            Travel travel = modelMapper.map(tr, Travel.class);
            travel.setDriver(driver);
            repository.save(travel);
            result.add(modelMapper.map(travel, TravelDTO.class));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelDTO> list(Long idClient, Long idDriver) {
        Client client = clientRepository.findById(idClient).orElseThrow(()-> new ResourceNotFoundException("Client not found"));
        Driver driver = driverRepository.findById(idDriver).orElseThrow(()-> new ResourceNotFoundException("Driver not found"));
        driver.setClient(client);
        if(driver.getTravels().isEmpty()) throw new NoContentException("Travels is empty");
        return driver.getTravels().stream().map(dr -> modelMapper.map(dr, TravelDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long idClient, Long idDriver) {
        Client client = clientRepository.findById(idClient).orElseThrow(()-> new ResourceNotFoundException("Client not found"));
        Driver driver = driverRepository.findById(idDriver).orElseThrow(()-> new ResourceNotFoundException("Driver not found"));
        driver.setClient(client);
        if(driver.getTravels().isEmpty()) throw new NoContentException("Travels is empty");
        driver.getTravels().forEach(op -> {
            repository.delete(op);            
        });                      
    }
}
