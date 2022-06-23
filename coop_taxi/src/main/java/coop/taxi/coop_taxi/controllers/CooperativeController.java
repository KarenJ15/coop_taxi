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

import coop.taxi.coop_taxi.DTO.CooperativeDTO;
import coop.taxi.coop_taxi.DTO.NewCooperativeDTO;
import coop.taxi.coop_taxi.services.CooperativeService;

@RestController
@RequestMapping("/cooperative")
public class CooperativeController {
    private final CooperativeService service;
    
    @Autowired
    public CooperativeController (CooperativeService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<CooperativeDTO> create(@Valid @RequestBody NewCooperativeDTO cooperativeDTO){
        CooperativeDTO result = service.create(cooperativeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CooperativeDTO> retrieve(@PathVariable("id") Long id){
        CooperativeDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<List<CooperativeDTO>> list(){
        List <CooperativeDTO> result = service.list();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CooperativeDTO> update(@RequestBody CooperativeDTO cooperativeDTO, @PathVariable("id") Long id){
        CooperativeDTO result = service.update(cooperativeDTO, id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Cooperativa eliminada");
    }
}
