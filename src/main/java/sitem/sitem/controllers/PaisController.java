package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Pais;
import sitem.sitem.service.PaisService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "pais")
public class PaisController {

    private final PaisService service;

    public PaisController(PaisService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Integer id) {
        Pais pais = service.findById(id);
        return ResponseEntity.ok(pais);
    }
    @GetMapping
    public ResponseEntity<List<Pais>> findAll() {
        List<Pais> pais = service.findAll();
        return ResponseEntity.ok(pais);
    }

    @PostMapping
    public ResponseEntity<Pais> create(@Valid @RequestBody Pais objDTO) {
        Pais newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdPais()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pais> update(@PathVariable Integer id, @Valid @RequestBody Pais objDTO) {
        Pais obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pais> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
