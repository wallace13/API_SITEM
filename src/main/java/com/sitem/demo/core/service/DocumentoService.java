package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Documento;
import com.sitem.demo.core.repository.DocumentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    public Documento findById(Integer id) {
        Optional<Documento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Documento> findAll() {
        return repository.findAll();
    }

    public Documento create(Documento Documento) {
        Documento.setIdDocumento(0);

        LocalDateTime now = LocalDateTime.now();
        Documento.setCreatedAt(now);
        Documento.setUpdatedAt(now);

        return repository.save(Documento);
    }

    public Documento update(Integer id, @Valid Documento Documento) {
        // Verifique se a entidade existe antes de tentar atualizar
        Documento existingDocumento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        // Atualize as propriedades da entidade existente
        existingDocumento.setDescricao(Documento.getDescricao());
        existingDocumento.setTipo(Documento.getTipo());
        existingDocumento.setCodBarras(Documento.getCodBarras());

        // Atualize a data de atualização
        existingDocumento.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingDocumento);
    }

    public void delete(Integer id) {
        // Verifique se o Documento existe
        Documento existingDocumento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        repository.deleteById(id);
    }
}
