package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Conteudo;
import sitem.sitem.service.ConteudoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "conteudo")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

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
