package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Colaborador;
import sitem.sitem.service.ColaboradorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

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
