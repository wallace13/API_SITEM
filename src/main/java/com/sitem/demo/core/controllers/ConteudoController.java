package com.sitem.demo.core.controllers;

import com.sitem.demo.core.model.Conteudo;
import com.sitem.demo.core.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "conteudo")
public class ConteudoController {

    @Autowired
    private ConteudoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conteudo> findById(@PathVariable Integer id) {
        Conteudo conteudo = service.findById(id);
        return ResponseEntity.ok(conteudo);
    }
    @GetMapping
    public ResponseEntity<List<Conteudo>> findAll() {
        List<Conteudo> conteudos = service.findAll();
        return ResponseEntity.ok(conteudos);
    }

    @PostMapping
    public ResponseEntity<Conteudo> create(@Valid @RequestBody Conteudo objDTO) {
        Conteudo newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdConteudo()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Conteudo> update(@PathVariable Integer id, @Valid @RequestBody Conteudo objDTO) {
        Conteudo obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Conteudo> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
