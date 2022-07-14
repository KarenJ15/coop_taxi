package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.CooperativeTravelDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;

public interface CooperativeService {
    public CooperativeDTO create(Long idTravel, NewCooperativeDTO cooperativeDTO);
    public CooperativeTravelDTO retrieve(Long idTravel, Long id);
    public CooperativeTravelDTO update(CooperativeDTO cooperativeDTO, Long idTravel, Long id);
    public void delete(Long idTravel, Long id);

    public List<CooperativeDTO> list(Long idTravel);
}
