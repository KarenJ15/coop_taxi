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

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;
import coop.taxi.coop_taxi.services.TravelService;

@RestController
@RequestMapping("/travel")
public class TravelController {
    private final TravelService service;
    
    @Autowired
    public TravelController (TravelService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<TravelDTO> create(@Valid @RequestBody NewTravelDTO travelDTO){
        TravelDTO result = service.create(travelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TravelDTO> retrieve(@PathVariable("id") Long id){
        TravelDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<List<TravelDTO>> list(){
        List <TravelDTO> result = service.list();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TravelDTO> update(@RequestBody TravelDTO travelDTO, @PathVariable("id") Long id){
        TravelDTO result = service.update(travelDTO, id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Viaje eliminado");
    }
}
