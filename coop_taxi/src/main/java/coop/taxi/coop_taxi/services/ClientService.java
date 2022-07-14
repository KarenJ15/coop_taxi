package coop.taxi.coop_taxi.services;

import java.util.List;

import coop.taxi.coop_taxi.DTO.ClientDTO;
import coop.taxi.coop_taxi.DTO.ClientListDTO;
import coop.taxi.coop_taxi.DTO.NewClientDTO;

public interface ClientService {
    public ClientDTO create(NewClientDTO clientDTO);
    public ClientDTO retrieve(Long id);
    public ClientDTO update(ClientDTO clientDTO, Long id);
    public void delete (Long id);
    public long count();

    public List<ClientListDTO> list(int page, int size, String sort);
}
