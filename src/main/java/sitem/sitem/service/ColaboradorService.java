package sitem.sitem.service;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Colaborador;
import sitem.sitem.repository.ColaboradorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    public Colaborador findById(Integer id) {
        Optional<Colaborador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Colaborador create(Colaborador colaborador) {
        colaborador.setIdColaborador(0);

        LocalDateTime now = LocalDateTime.now();
        colaborador.setCreatedAt(now);
        colaborador.setUpdatedAt(now);

        return repository.save(colaborador);
    }

    public Colaborador update(Integer id, @Valid Colaborador colaborador) {
        // Verifique se a entidade existe antes de tentar atualizar
        Colaborador existingColaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        // Atualize as propriedades da entidade existente
        existingColaborador.setMatricula(colaborador.getMatricula());
        existingColaborador.setNome(colaborador.getNome());
        existingColaborador.setEmail(colaborador.getEmail());
        existingColaborador.setCargo(colaborador.getCargo());
        existingColaborador.setUnidade(colaborador.getUnidade());

        // Atualize a data de atualização
        existingColaborador.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingColaborador);
    }

    public void delete(Integer id) {
        // Verifique se a Colaborador existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        repository.deleteById(id);
    }
}
