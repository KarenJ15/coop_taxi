package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Cooperative;
import coop.taxi.coop_taxi.repositories.CooperativeRepository;
import coop.taxi.coop_taxi.services.CooperativeService;

@Service
public class CooperativeServiceImpl implements CooperativeService{
    final ModelMapper modelMapper;
    final CooperativeRepository cooperativeRepository;

    @Autowired
    public CooperativeServiceImpl(@Autowired CooperativeRepository repository, ModelMapper mapper){
        this.cooperativeRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public CooperativeDTO create(NewCooperativeDTO cooperativeDTO){
        Cooperative cooperative = modelMapper.map(cooperativeDTO, Cooperative.class);
        cooperativeRepository.save(cooperative);
        return modelMapper.map(cooperative, CooperativeDTO.class);
    }
 
    @Override
    @Transactional(readOnly = true)
    public CooperativeDTO retrieve(Long id){
        Cooperative cooperative = cooperativeRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cooperativa no encontrada"));
        return modelMapper.map(cooperative, CooperativeDTO.class);
    }

    @Override
    @Transactional
    public CooperativeDTO update(CooperativeDTO cooperativeDTO, Long id){
        Cooperative cooperative = cooperativeRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cooperativa no encontrada"));
        cooperative.setId(id);
        cooperative  = modelMapper.map(cooperativeDTO, Cooperative.class);
        cooperativeRepository.save(cooperative);
        return modelMapper.map(cooperative, CooperativeDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Cooperative cooperative = cooperativeRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cooperativa no encontrada"));
        cooperativeRepository.deleteById(cooperative.getId());
    }  
    
    @Override
    @Transactional(readOnly = true)
    public List<CooperativeDTO> list(){
        List<Cooperative> cooperatives = cooperativeRepository.findAll();
        return cooperatives.stream().map(cooperative -> modelMapper.map(cooperative,CooperativeDTO.class))
        .collect(Collectors.toList());
    }
}
