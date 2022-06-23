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

import coop.taxi.coop_taxi.DTO.DriverDTO;
import coop.taxi.coop_taxi.DTO.NewDriverDTO;
import coop.taxi.coop_taxi.services.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;
    
    @Autowired
    public DriverController (DriverService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<DriverDTO> create(@Valid @RequestBody NewDriverDTO driverDTO){
        DriverDTO result = service.create(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> retrieve(@PathVariable("id") Long id){
        DriverDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<List<DriverDTO>> list(){
        List <DriverDTO> result = service.list();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(@RequestBody DriverDTO driverDTO, @PathVariable("id") Long id){
        DriverDTO result = service.update(driverDTO, id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Conductor eliminado");
    }
}
