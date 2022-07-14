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

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.CooperativeTravelDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;
import coop.taxi.coop_taxi.services.CooperativeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cooperatives")
public class CooperativeController {
    
    final CooperativeService service;

    public CooperativeController(CooperativeService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/cooperatives")
    public ResponseEntity<CooperativeDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewCooperativeDTO cooperativeDTO){
        CooperativeDTO cooperativDTO = service.create(id, cooperativeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cooperativDTO);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{idTravel}/cooperatives/{id}")
    public ResponseEntity<CooperativeTravelDTO> retrive(@PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        CooperativeTravelDTO result = service.retrieve(idTravel, id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idTravel}/cooperatives/{id}")
    public ResponseEntity<CooperativeTravelDTO> update(@RequestBody CooperativeDTO cooperativeDTO, @PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        CooperativeTravelDTO result = service.update(cooperativeDTO, idTravel, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idTravel}/cooperatives/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idTravel") Long idTravel, @PathVariable("id") Long id){
        service.delete(idTravel, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/cooperatives")
    public ResponseEntity<List<CooperativeDTO>> list(@PathVariable("id") Long id){
        List<CooperativeDTO> cooperatives = service.list(id);
        return ResponseEntity.ok().body(cooperatives);        
    }

}
