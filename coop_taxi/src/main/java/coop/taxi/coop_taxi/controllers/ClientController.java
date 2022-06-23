package coop.taxi.coop_taxi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.taxi.coop_taxi.DTO.ClientDTO;
import coop.taxi.coop_taxi.DTO.NewClientDTO;
import coop.taxi.coop_taxi.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;
    
    @Autowired
    public ClientController (ClientService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody NewClientDTO clientDTO){
        ClientDTO result = service.create(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> retrieve(@PathVariable("id") Long id){
        ClientDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<List<ClientDTO>> list(){
        List <ClientDTO> result = service.list();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO, @PathVariable("id") Long id){
        ClientDTO result = service.update(clientDTO, id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Cliente eliminado");
    }
}
