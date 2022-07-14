package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.ClientDTO;
import coop.taxi.coop_taxi.DTO.ClientListDTO;
import coop.taxi.coop_taxi.DTO.NewClientDTO;
import coop.taxi.coop_taxi.exceptions.NoContentException;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Client;
import coop.taxi.coop_taxi.repositories.ClientRepository;
import coop.taxi.coop_taxi.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
    final ModelMapper modelMapper;
    final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository repository, ModelMapper mapper){
        this.clientRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ClientDTO create(NewClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);        
        return modelMapper.map(client, ClientDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO retrieve(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Client not found"));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO update(ClientDTO clientDTO, Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client not found"));        
              
                Client clientUpdated = modelMapper.map(clientDTO, Client.class);
        //Keeping values
        clientUpdated.setCreatedBy(client.getCreatedBy());
        clientUpdated.setCreatedDate(client.getCreatedDate());
        clientRepository.save(clientUpdated);   
        return modelMapper.map(clientUpdated, ClientDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client not found"));        
                clientRepository.deleteById(client.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Client> clients = clientRepository.findAll(pageable);
        if(clients.isEmpty()) throw new NoContentException("clients is empty");
        return clients.stream().map(client -> modelMapper.map(client, ClientListDTO.class))
            .collect(Collectors.toList());        
    }

    @Override
    public long count() {        
        return clientRepository.count();
    }
}
