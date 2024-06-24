package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Malote;
import sitem.sitem.service.MaloteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "malote")
public class MaloteController {

    private final MaloteService service;

    public MaloteController(MaloteService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Malote> findById(@PathVariable Integer id) {
        Malote malote = service.findById(id);
        return ResponseEntity.ok(malote);
    }
    @GetMapping
    public ResponseEntity<List<Malote>> findAll() {
        List<Malote> malotes = service.findAll();
        return ResponseEntity.ok(malotes);
    }

    @PostMapping
    public ResponseEntity<Malote> create(@Valid @RequestBody Malote objDTO) {
        Malote newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdMalote()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Malote> update(@PathVariable Integer id, @Valid @RequestBody Malote objDTO) {
        Malote obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Malote> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
