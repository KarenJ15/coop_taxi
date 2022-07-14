package coop.taxi.coop_taxi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.taxi.coop_taxi.DTO.ClientDTO;
import coop.taxi.coop_taxi.DTO.ClientListDTO;
import coop.taxi.coop_taxi.DTO.NewClientDTO;
import coop.taxi.coop_taxi.services.ClientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;
    
    public ClientController(ClientService srv){
        this.service =srv;
    }
    
    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody NewClientDTO clientDTO){
        ClientDTO result = service.create(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> retrive(@PathVariable("id") Long id){
        ClientDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO, @PathVariable("id") Long id){
        ClientDTO result = service.update(clientDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<ClientListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<ClientListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }

     /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }
}
