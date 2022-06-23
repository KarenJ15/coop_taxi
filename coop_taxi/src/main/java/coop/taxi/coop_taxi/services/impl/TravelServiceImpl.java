package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Travel;
import coop.taxi.coop_taxi.repositories.TravelRepository;
import coop.taxi.coop_taxi.services.TravelService;

@Service
public class TravelServiceImpl implements TravelService{
    final ModelMapper modelMapper;
    final TravelRepository travelRepository;

    @Autowired
    public TravelServiceImpl(@Autowired TravelRepository repository, ModelMapper mapper){
        this.travelRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public TravelDTO create(NewTravelDTO travelDTO){
        Travel travel = modelMapper.map(travelDTO, Travel.class);
        travelRepository.save(travel);
        return modelMapper.map(travel, TravelDTO.class);
    }
 
    @Override
    @Transactional(readOnly = true)
    public TravelDTO retrieve(Long id){
        Travel travel = travelRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Viaje no encontrado"));
        return modelMapper.map(travel, TravelDTO.class);
    }

    @Override
    @Transactional
    public TravelDTO update(TravelDTO travelDTO, Long id){
        Travel travel = travelRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Viaje no encontrado"));
        travel.setId(id);
        travel  = modelMapper.map(travelDTO, Travel.class);
        travelRepository.save(travel);
        return modelMapper.map(travel, TravelDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Travel travel = travelRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Viaje no encontrado"));
        travelRepository.deleteById(travel.getId());
    }  
    
    @Override
    @Transactional(readOnly = true)
    public List<TravelDTO> list(){
        List<Travel> travels = travelRepository.findAll();
        return travels.stream().map(travel -> modelMapper.map(travel,TravelDTO.class))
        .collect(Collectors.toList());
    }
}
