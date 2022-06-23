package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;

public interface CooperativeService {
    public CooperativeDTO create(NewCooperativeDTO cooperativeDTO);
    public CooperativeDTO retrieve(Long id);
    public CooperativeDTO update(CooperativeDTO cooperativeDTO, Long id);
    public void delete (Long id);

    public List<CooperativeDTO> list();
}
