package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;

public interface TravelService {
    public TravelDTO create(NewTravelDTO travelDTO);
    public TravelDTO retrieve(Long id);
    public TravelDTO update(TravelDTO travelDTO, Long id);
    public void delete (Long id);

    public List<TravelDTO> list();
}
