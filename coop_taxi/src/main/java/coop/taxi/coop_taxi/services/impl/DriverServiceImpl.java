package coop.taxi.coop_taxi.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.DriverDTO;
import coop.taxi.coop_taxi.DTO.DriverListDTO;
import coop.taxi.coop_taxi.DTO.NewDriverDTO;
import coop.taxi.coop_taxi.exceptions.NoContentException;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Driver;
import coop.taxi.coop_taxi.repositories.DriverRepository;
import coop.taxi.coop_taxi.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
    final ModelMapper modelMapper;
    final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository repository, ModelMapper mapper){
        this.driverRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public DriverDTO create(NewDriverDTO driverDTO) {
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        driverRepository.save(driver);        
        return modelMapper.map(driver, DriverDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public DriverDTO retrieve(Long id) {
        Driver driver = driverRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Driver not found"));
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    @Transactional
    public DriverDTO update(DriverDTO driverDTO, Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not found"));        
              
                Driver driverUpdated = modelMapper.map(driverDTO, Driver.class);
        //Keeping values
        driverUpdated.setCreatedBy(driver.getCreatedBy());
        driverUpdated.setCreatedDate(driver.getCreatedDate());
        driverRepository.save(driverUpdated);   
        return modelMapper.map(driverUpdated, DriverDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not found"));        
                driverRepository.deleteById(driver.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Driver> drivers = driverRepository.findAll(pageable);
        if(drivers.isEmpty()) throw new NoContentException("drivers is empty");
        return drivers.stream().map(driver -> modelMapper.map(driver, DriverListDTO.class))
            .collect(Collectors.toList());        
    }

    @Override
    public long count() {        
        return driverRepository.count();
    }

}
