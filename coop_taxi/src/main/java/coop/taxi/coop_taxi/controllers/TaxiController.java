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

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;
import coop.taxi.coop_taxi.services.TaxiService;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    private final TaxiService service;
    
    @Autowired
    public TaxiController (TaxiService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<TaxiDTO> create(@Valid @RequestBody NewTaxiDTO taxiDTO){
        TaxiDTO result = service.create(taxiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaxiDTO> retrieve(@PathVariable("id") Long id){
        TaxiDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<List<TaxiDTO>> list(){
        List <TaxiDTO> result = service.list();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaxiDTO> update(@RequestBody TaxiDTO taxiDTO, @PathVariable("id") Long id){
        TaxiDTO result = service.update(taxiDTO, id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Taxi eliminado");
    }
}
