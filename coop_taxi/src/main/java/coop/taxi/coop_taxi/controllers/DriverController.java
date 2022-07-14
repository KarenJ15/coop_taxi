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

import coop.taxi.coop_taxi.DTO.DriverDTO;
import coop.taxi.coop_taxi.DTO.DriverListDTO;
import coop.taxi.coop_taxi.DTO.NewDriverDTO;
import coop.taxi.coop_taxi.services.DriverService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;
    
    public DriverController(DriverService srv){
        this.service =srv;
    }
    
    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<DriverDTO> create(@Valid @RequestBody NewDriverDTO driverDTO){
        DriverDTO result = service.create(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> retrive(@PathVariable("id") Long id){
        DriverDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(@RequestBody DriverDTO driverDTO, @PathVariable("id") Long id){
        DriverDTO result = service.update(driverDTO, id);
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
    public ResponseEntity<List<DriverListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<DriverListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }

     /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }
}
