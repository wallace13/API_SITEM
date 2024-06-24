package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Historico;
import sitem.sitem.service.HistoricoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "historico")
public class HistoricoController {

    private final HistoricoService service;

    public HistoricoController(HistoricoService service) {
        this.service = service;
    }

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
