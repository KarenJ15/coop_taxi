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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.taxi.coop_taxi.DTO.NewTravelDTO;
import coop.taxi.coop_taxi.DTO.TravelDTO;
import coop.taxi.coop_taxi.services.TravelService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clients")
public class TravelController {
    
    final TravelService service;

    public TravelController(TravelService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/drivers/{idDriver}/travels")
    public ResponseEntity<List<TravelDTO>> create(@PathVariable("id") Long id, @PathVariable("idDriver") Long idDriver, @Valid @RequestBody List<NewTravelDTO> travelsDTO){
        List<TravelDTO> travelDTOs = service.create(id, idDriver, travelsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(travelDTOs);        
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}/drivers/{idDriver}/travels")
    public ResponseEntity<List<TravelDTO>> delete(@PathVariable("id") Long id, @PathVariable("idDriver") Long idDriver){
        service.remove(id, idDriver);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/drivers/{idDriver}/travels")
    public ResponseEntity<List<TravelDTO>> list(@PathVariable("id") Long id, @PathVariable("idDriver") Long idDriver){
        List<TravelDTO> travelDTOs = service.list(id, idDriver);
        return ResponseEntity.status(HttpStatus.OK).body(travelDTOs);        
    }
}
