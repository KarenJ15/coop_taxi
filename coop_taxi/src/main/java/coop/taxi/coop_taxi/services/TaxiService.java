package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;

public interface TaxiService {
    public TaxiDTO create(NewTaxiDTO taxiDTO);
    public TaxiDTO retrieve(Long id);
    public TaxiDTO update(TaxiDTO taxiDTO, Long id);
    public void delete (Long id);

    public List<TaxiDTO> list();
}
