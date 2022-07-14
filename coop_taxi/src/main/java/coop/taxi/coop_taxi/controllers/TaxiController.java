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
import org.springframework.web.bind.annotation.RestController;

import coop.taxi.coop_taxi.DTO.NewTaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiDTO;
import coop.taxi.coop_taxi.DTO.TaxiTravelDTO;
import coop.taxi.coop_taxi.services.TaxiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/taxis")
public class TaxiController {
    
    final TaxiService service;

    public TaxiController(TaxiService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/taxis")
    public ResponseEntity<TaxiDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewTaxiDTO taxiDTO){
        TaxiDTO taxDTO = service.create(id, taxiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taxDTO);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{idTravel}/taxis/{id}")
    public ResponseEntity<TaxiTravelDTO> retrive(@PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        TaxiTravelDTO result = service.retrieve(idTravel, id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idTravel}/taxis/{id}")
    public ResponseEntity<TaxiTravelDTO> update(@RequestBody TaxiDTO taxiDTO, @PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        TaxiTravelDTO result = service.update(taxiDTO, idTravel, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idTravel}/taxis/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        service.delete(idTravel, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/taxis")
    public ResponseEntity<List<TaxiDTO>> list(@PathVariable("id") Long id){
        List<TaxiDTO> taxis = service.list(id);
        return ResponseEntity.ok().body(taxis);        
    }
}
