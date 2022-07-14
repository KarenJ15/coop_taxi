package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.CooperativeTravelDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;
import coop.taxi.coop_taxi.exceptions.NoContentException;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Cooperative;
import coop.taxi.coop_taxi.models.Travel;
import coop.taxi.coop_taxi.repositories.CooperativeRepository;
import coop.taxi.coop_taxi.repositories.TravelRepository;
import coop.taxi.coop_taxi.services.CooperativeService;

@Service
public class CooperativeServiceImpl implements CooperativeService{
    final ModelMapper modelMapper;
    final CooperativeRepository repository;
    final TravelRepository travelRepository;

    public CooperativeServiceImpl(CooperativeRepository r, TravelRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.travelRepository = er;
    }


    @Override
    @Transactional
    public CooperativeDTO create(Long idTravel, NewCooperativeDTO cooperativeDTO) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Cooperative cooperative = modelMapper.map(cooperativeDTO, Cooperative.class);    
        cooperative.setTravel(travel);
        repository.save(cooperative);
        return modelMapper.map(cooperative, CooperativeDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public CooperativeTravelDTO retrieve(Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Cooperative cooperative = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cooperative not found"));
        cooperative.setTravel(travel);
        return modelMapper.map(cooperative, CooperativeTravelDTO.class);
    }

    @Override
    @Transactional
    public CooperativeTravelDTO update(CooperativeDTO cooperativeDTO, Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
        .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Cooperative cooperative = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cooperative not found"));
        cooperative = modelMapper.map(cooperativeDTO, Cooperative.class);
        cooperative.setTravel(travel);
        repository.save(cooperative);       
        return modelMapper.map(cooperative, CooperativeTravelDTO.class);
    }


    @Override
    @Transactional
    public void delete(Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
        .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Cooperative cooperative = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cooperative not found"));
        cooperative.setTravel(travel);
        repository.deleteById(cooperative.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<CooperativeDTO> list(Long idTravel) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        List<Cooperative> cooperatives = repository.findByTravel(travel);
        if(cooperatives.isEmpty()) throw new NoContentException("Cooperatives is empty");
        //Lambda ->
        return cooperatives.stream().map(q -> modelMapper.map(q, CooperativeDTO.class) )
            .collect(Collectors.toList());
    }
}
