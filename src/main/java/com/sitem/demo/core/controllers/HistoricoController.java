package com.sitem.demo.core.controllers;

import com.sitem.demo.core.model.Historico;
import com.sitem.demo.core.service.HistoricoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Historico> findById(@PathVariable Integer id) {
        Historico historico = service.findById(id);
        return ResponseEntity.ok(historico);
    }
    @GetMapping
    public ResponseEntity<List<Historico>> findAll() {
        List<Historico> historicos = service.findAll();
        return ResponseEntity.ok(historicos);
    }

    @PostMapping
    public ResponseEntity<Historico> create(@Valid @RequestBody Historico objDTO) {
        Historico newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdHistorico()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Historico> update(@PathVariable Integer id, @Valid @RequestBody Historico objDTO) {
        Historico obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Historico> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
