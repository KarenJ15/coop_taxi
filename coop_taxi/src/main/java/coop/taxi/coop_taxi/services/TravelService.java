package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;

public interface TravelService {
    public List<TravelDTO> create(Long idClient, Long idDriver, List<NewTravelDTO> list);
    public List<TravelDTO> list(Long idClient, Long idDriver);
    public void remove(Long idClient, Long idDriver);
}
