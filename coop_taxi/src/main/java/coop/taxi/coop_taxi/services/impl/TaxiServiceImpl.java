package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiTravelDTO;
import coop.taxi.coop_taxi.exceptions.NoContentException;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Taxi;
import coop.taxi.coop_taxi.models.Travel;
import coop.taxi.coop_taxi.repositories.TaxiRepository;
import coop.taxi.coop_taxi.repositories.TravelRepository;
import coop.taxi.coop_taxi.services.TaxiService;

@Service
public class TaxiServiceImpl implements TaxiService{
    final ModelMapper modelMapper;
    final TaxiRepository repository;
    final TravelRepository travelRepository;

    public TaxiServiceImpl(TaxiRepository r, TravelRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.travelRepository = er;
    }


    @Override
    @Transactional
    public TaxiDTO create(Long idTravel, NewTaxiDTO taxiDTO) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
            Taxi taxi = modelMapper.map(taxiDTO, Taxi.class);    
            taxi.setTravel(travel);
        repository.save(taxi);
        return modelMapper.map(taxi, TaxiDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public TaxiTravelDTO retrieve(Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Taxi taxi = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        taxi.setTravel(travel);
        return modelMapper.map(taxi, TaxiTravelDTO.class);
    }

    @Override
    @Transactional
    public TaxiTravelDTO update(TaxiDTO taxiDTO, Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
        .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Taxi taxi = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        taxi = modelMapper.map(taxiDTO, Taxi.class);
        taxi.setTravel(travel);
        repository.save(taxi);       
        return modelMapper.map(taxi, TaxiTravelDTO.class);
    }


    @Override
    @Transactional
    public void delete(Long idTravel, Long id) {
        Travel travel = travelRepository.findById(idTravel)
        .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        Taxi taxi = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        taxi.setTravel(travel);
        repository.deleteById(taxi.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<TaxiDTO> list(Long idTravel) {
        Travel travel = travelRepository.findById(idTravel)
            .orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        List<Taxi> taxis = repository.findByTravel(travel);
        if(taxis.isEmpty()) throw new NoContentException("Taxi is empty");
        //Lambda ->
        return taxis.stream().map(q -> modelMapper.map(q, TaxiDTO.class) )
            .collect(Collectors.toList());
    }
}
