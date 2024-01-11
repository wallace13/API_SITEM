package com.sitem.demo.core.controllers;

import com.sitem.demo.core.model.Unidade;
import com.sitem.demo.core.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Integer id) {
        Unidade unidade = service.findById(id);
        return ResponseEntity.ok(unidade);
    }
    @GetMapping
    public ResponseEntity<List<Unidade>> findAll() {
        List<Unidade> unidades = service.findAll();
        return ResponseEntity.ok(unidades);
    }

    @PostMapping
    public ResponseEntity<Unidade> create(@Valid @RequestBody Unidade objDTO) {
        Unidade newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdUnidade()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Unidade> update(@PathVariable Integer id, @Valid @RequestBody Unidade objDTO) {
        Unidade obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Unidade> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
