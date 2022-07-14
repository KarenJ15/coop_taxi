package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.DriverDTO;
import coop.taxi.coop_taxi.DTO.DriverListDTO;
import coop.taxi.coop_taxi.DTO.NewDriverDTO;

public interface DriverService {
    public DriverDTO create(NewDriverDTO taxiDTO);
    public DriverDTO retrieve(Long id);
    public DriverDTO update(DriverDTO driverDTO, Long id);
    public void delete (Long id);
    public long count();

    public List<DriverListDTO> list(int page, int size, String sort);
}
