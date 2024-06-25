package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Cidade;
import sitem.sitem.service.CidadeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "cidade")
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Integer id) {
        Cidade cidades = service.findById(id);
        return ResponseEntity.ok(cidades);
    }
    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> cidades = service.findAll();
        return ResponseEntity.ok(cidades);
    }

    @PostMapping
    public ResponseEntity<Cidade> create(@Valid @RequestBody Cidade objDTO) {
        Cidade newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdCidade()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cidade> update(@PathVariable Integer id, @Valid @RequestBody Cidade objDTO) {
        Cidade obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cidade> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
