package sitem.sitem.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Malote;
import sitem.sitem.repository.DocumentoRepository;
import sitem.sitem.repository.MaloteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaloteService {

    private final MaloteRepository repository;

    private final DocumentoRepository documentoRepository;

    public MaloteService(MaloteRepository repository, DocumentoRepository documentoRepository) {
        this.repository = repository;
        this.documentoRepository = documentoRepository;
    }

    public Malote findById(Integer id) {
        Optional<Malote> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Malote> findAll() {
        return repository.findAll();
    }

    public Malote create(Malote malote) {
        malote.setIdMalote(0);

        LocalDateTime now = LocalDateTime.now();
        malote.setCreatedAt(now);
        malote.setUpdatedAt(now);

        return repository.save(malote);
    }

    public Malote update(Integer id, @Valid Malote malote) {
        // Verifique se a entidade existe antes de tentar atualizar
        Malote existingMalote = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Malote não encontrada"));

        // Atualize as propriedades da entidade existente
        existingMalote.setIdLacre(malote.getIdLacre());
        existingMalote.setStatus(malote.getStatus());
        existingMalote.setDtEntrada(malote.getDtEntrada());
        existingMalote.setHrEntrada(malote.getHrEntrada());
        existingMalote.setDtSaida(malote.getDtSaida());
        existingMalote.setHrSaida(malote.getHrSaida());

        // Atualize a data de atualização
        existingMalote.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingMalote);
    }

    public void delete(Integer id) {
        // Verifique se o Malote existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Malote não encontrado"));

        // Se não pertence a nenhum colaborador, exclua a Malote
        repository.deleteById(id);
    }
}
