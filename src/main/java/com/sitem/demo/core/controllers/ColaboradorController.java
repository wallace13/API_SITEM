package com.sitem.demo.core.controllers;

import com.sitem.demo.core.model.Colaborador;
import com.sitem.demo.core.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Integer id) {
        Colaborador colaborador = service.findById(id);
        return ResponseEntity.ok(colaborador);
    }
    @GetMapping
    public ResponseEntity<List<Colaborador>> findAll() {
        List<Colaborador> colaboradores = service.findAll();
        return ResponseEntity.ok(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> create(@Valid @RequestBody Colaborador objDTO) {
        Colaborador newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdColaborador()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Integer id, @Valid @RequestBody Colaborador objDTO) {
        Colaborador obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Colaborador> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
