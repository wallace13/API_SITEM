package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Documento;
import com.sitem.demo.core.model.Colaborador;
import com.sitem.demo.core.repository.DocumentoRepository;
import com.sitem.demo.core.repository.ColaboradorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Colaborador findById(Integer id) {
        Optional<Colaborador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Colaborador create(Colaborador Colaborador) {
        Colaborador.setIdColaborador(0);

        LocalDateTime now = LocalDateTime.now();
        Colaborador.setCreatedAt(now);
        Colaborador.setUpdatedAt(now);

        return repository.save(Colaborador);
    }

    public Colaborador update(Integer id, @Valid Colaborador Colaborador) {
        // Verifique se a entidade existe antes de tentar atualizar
        Colaborador existingColaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        // Atualize as propriedades da entidade existente
        existingColaborador.setMatricula(Colaborador.getMatricula());
        existingColaborador.setNome(Colaborador.getNome());
        existingColaborador.setEmail(Colaborador.getEmail());
        existingColaborador.setCargo(Colaborador.getCargo());
        existingColaborador.setUnidade(Colaborador.getUnidade());

        // Atualize a data de atualização
        existingColaborador.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingColaborador);
    }

    public void delete(Integer id) {
        // Verifique se a Colaborador existe
        Colaborador existingColaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        repository.deleteById(id);
    }
}
