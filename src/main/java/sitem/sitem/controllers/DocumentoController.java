package sitem.sitem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sitem.sitem.model.Documento;
import sitem.sitem.service.DocumentoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "documento")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Documento> findById(@PathVariable Integer id) {
        Documento documento = service.findById(id);
        return ResponseEntity.ok(documento);
    }
    @GetMapping
    public ResponseEntity<List<Documento>> findAll() {
        List<Documento> documentos = service.findAll();
        return ResponseEntity.ok(documentos);
    }

    @PostMapping
    public ResponseEntity<Documento> create(@Valid @RequestBody Documento objDTO) {
        Documento newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdDocumento()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Documento> update(@PathVariable Integer id, @Valid @RequestBody Documento objDTO) {
        Documento obj = service.update(id, objDTO);
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Documento> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
