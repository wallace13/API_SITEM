package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Estado;
import sitem.sitem.service.EstadoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "estado")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Integer id) {
        Estado estados = service.findById(id);
        return ResponseEntity.ok(estados);
    }
    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estados = service.findAll();
        return ResponseEntity.ok(estados);
    }

    @PostMapping
    public ResponseEntity<Estado> create(@Valid @RequestBody Estado objDTO) {
        Estado newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdEstado()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estado> update(@PathVariable Integer id, @Valid @RequestBody Estado objDTO) {
        Estado obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Estado> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
