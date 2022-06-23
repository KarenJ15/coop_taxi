package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Taxi;
import coop.taxi.coop_taxi.repositories.TaxiRepository;
import coop.taxi.coop_taxi.services.TaxiService;

@Service
public class TaxiServiceImpl implements TaxiService{
    final ModelMapper modelMapper;
    final TaxiRepository taxiRepository;

    @Autowired
    public TaxiServiceImpl(@Autowired TaxiRepository repository, ModelMapper mapper){
        this.taxiRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public TaxiDTO create(NewTaxiDTO taxiDTO){
        Taxi taxi = modelMapper.map(taxiDTO, Taxi.class);
        taxiRepository.save(taxi);
        return modelMapper.map(taxi, TaxiDTO.class);
    }
 
    @Override
    @Transactional(readOnly = true)
    public TaxiDTO retrieve(Long id){
        Taxi taxi = taxiRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Taxi no encontrado"));
        return modelMapper.map(taxi, TaxiDTO.class);
    }

    @Override
    @Transactional
    public TaxiDTO update(TaxiDTO taxiDTO, Long id){
        Taxi taxi = taxiRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Taxi no encontrado"));
        taxi.setId(id);
        taxi  = modelMapper.map(taxiDTO, Taxi.class);
        taxiRepository.save(taxi);
        return modelMapper.map(taxi, TaxiDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Taxi taxi = taxiRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Taxi no encontrado"));
        taxiRepository.deleteById(taxi.getId());
    }  
    
    @Override
    @Transactional(readOnly = true)
    public List<TaxiDTO> list(){
        List<Taxi> taxis = taxiRepository.findAll();
        return taxis.stream().map(taxi -> modelMapper.map(taxi,TaxiDTO.class))
        .collect(Collectors.toList());
    }
}
