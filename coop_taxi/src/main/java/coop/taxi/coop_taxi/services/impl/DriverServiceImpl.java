package coop.taxi.coop_taxi.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.DriverDTO;
import coop.taxi.coop_taxi.DTO.NewDriverDTO;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Driver;
import coop.taxi.coop_taxi.repositories.DriverRepository;
import coop.taxi.coop_taxi.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
    final ModelMapper modelMapper;
    final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(@Autowired DriverRepository repository, ModelMapper mapper){
        this.driverRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public DriverDTO create(NewDriverDTO driverDTO){
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        driverRepository.save(driver);
        return modelMapper.map(driver, DriverDTO.class);
    }
 
    @Override
    @Transactional(readOnly = true)
    public DriverDTO retrieve(Long id){
        Driver driver = driverRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Conductor no encontrado"));
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    @Transactional
    public DriverDTO update(DriverDTO driverDTO, Long id){
        Driver driver = driverRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Conductor no encontrado"));
        driver.setId(id);
        driver  = modelMapper.map(driverDTO, Driver.class);
        driverRepository.save(driver);
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Driver driver = driverRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Conductor no encontrado"));
        driverRepository.deleteById(driver.getId());
    }  
    
    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> list(){
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(driver -> modelMapper.map(driver,DriverDTO.class))
        .collect(Collectors.toList());
    }
}
