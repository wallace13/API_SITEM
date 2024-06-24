package sitem.sitem.service;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Documento;
import sitem.sitem.repository.DocumentoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    private final DocumentoRepository repository;

    public DocumentoService(DocumentoRepository repository) {
        this.repository = repository;
    }

    public Documento findById(Integer id) {
        Optional<Documento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Documento> findAll() {
        return repository.findAll();
    }

    public Documento create(Documento documento) {
        documento.setIdDocumento(0);

        LocalDateTime now = LocalDateTime.now();
        documento.setCreatedAt(now);
        documento.setUpdatedAt(now);

        return repository.save(documento);
    }

    public Documento update(Integer id, @Valid Documento documento) {
        // Verifique se a entidade existe antes de tentar atualizar
        Documento existingDocumento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        // Atualize as propriedades da entidade existente
        existingDocumento.setDescricao(documento.getDescricao());
        existingDocumento.setTipo(documento.getTipo());
        existingDocumento.setCodBarras(documento.getCodBarras());

        // Atualize a data de atualização
        existingDocumento.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingDocumento);
    }

    public void delete(Integer id) {
        // Verifique se o Documento existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        repository.deleteById(id);
    }
}
