package coop.taxi.coop_taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.taxi.coop_taxi.DTO.ClientDTO;
import coop.taxi.coop_taxi.DTO.NewClientDTO;
import coop.taxi.coop_taxi.exceptions.ResourceNotFoundException;
import coop.taxi.coop_taxi.models.Client;
import coop.taxi.coop_taxi.repositories.ClientRepository;
import coop.taxi.coop_taxi.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
    final ModelMapper modelMapper;
    final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(@Autowired ClientRepository repository, ModelMapper mapper){
        this.clientRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ClientDTO create(NewClientDTO clientDTO){
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }
 
    @Override
    @Transactional(readOnly = true)
    public ClientDTO retrieve(Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO update(ClientDTO clientDTO, Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
        client.setId(id);
        client  = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
        clientRepository.deleteById(client.getId());
    }  
    
    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> list(){
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> modelMapper.map(client,ClientDTO.class))
        .collect(Collectors.toList());
    }
}
