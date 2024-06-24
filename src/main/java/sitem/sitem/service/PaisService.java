package sitem.sitem.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Pais;
import sitem.sitem.repository.PaisRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    private final PaisRepository repository;


    public PaisService(PaisRepository repository) {
        this.repository = repository;
    }

    public Pais findById(Integer id) {
        Optional<Pais> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Pais> findAll() {
        return repository.findAll();
    }

    public Pais create(Pais pais) {
        pais.setIdPais(0);

        LocalDateTime now = LocalDateTime.now();
        pais.setCreatedAt(now);
        pais.setUpdatedAt(now);

        return repository.save(pais);
    }

    public Pais update(Integer id, @Valid Pais pais) {
        // Verifique se a entidade existe antes de tentar atualizar
        Pais existingPais = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        // Atualize as propriedades da entidade existente
        existingPais.setNomePortugues(pais.getNomePortugues());
        existingPais.setNomeIngles(pais.getNomeIngles());
        existingPais.setSigla(pais.getSigla());

        // Atualize a data de atualização
        existingPais.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingPais);
    }

    public void delete(Integer id) {
        // Verifique se a pais existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        // Se não pertence a nenhum colaborador, exclua a pais
        repository.deleteById(id);
    }
}
