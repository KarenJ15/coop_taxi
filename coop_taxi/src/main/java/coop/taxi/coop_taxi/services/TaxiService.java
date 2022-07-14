package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiTravelDTO;

public interface TaxiService {
    public TaxiDTO create(Long idTravel, NewTaxiDTO taxiDTO);
    public TaxiTravelDTO retrieve(Long idTravel, Long id);
    public TaxiTravelDTO update(TaxiDTO taxiDTO, Long idTravel, Long id);
    public void delete(Long idTravel, Long id);

    public List<TaxiDTO> list(Long idTravel);
}