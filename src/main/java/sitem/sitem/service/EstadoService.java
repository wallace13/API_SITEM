package sitem.sitem.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Estado;
import sitem.sitem.repository.EstadoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository repository;


    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public Estado findById(Integer id) {
        Optional<Estado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado create(Estado estado) {
        estado.setIdEstado(0);

        LocalDateTime now = LocalDateTime.now();
        estado.setCreatedAt(now);
        estado.setUpdatedAt(now);

        return repository.save(estado);
    }

    public Estado update(Integer id, @Valid Estado estado) {
        // Verifique se a entidade existe antes de tentar atualizar
        Estado existingEstado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        // Atualize as propriedades da entidade existente
        existingEstado.setNome(estado.getNome());;
        existingEstado.setSigla(estado.getSigla());
        existingEstado.setPais(estado.getPais());

        // Atualize a data de atualização
        existingEstado.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingEstado);
    }

    public void delete(Integer id) {
        // Verifique se estado existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        repository.deleteById(id);
    }
}
