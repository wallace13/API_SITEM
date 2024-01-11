package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Unidade;
import com.sitem.demo.core.repository.ColaboradorRepository;
import com.sitem.demo.core.repository.UnidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Unidade findById(Integer id) {
        Optional<Unidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Unidade> findAll() {
        return repository.findAll();
    }

    public Unidade create(Unidade unidade) {
        unidade.setIdUnidade(0);

        LocalDateTime now = LocalDateTime.now();
        unidade.setCreatedAt(now);
        unidade.setUpdatedAt(now);

        return repository.save(unidade);
    }

    public Unidade update(Integer id, @Valid Unidade unidade) {
        // Verifique se a entidade existe antes de tentar atualizar
        Unidade existingUnidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        // Atualize as propriedades da entidade existente
        existingUnidade.setNome(unidade.getNome());
        existingUnidade.setEndereco(unidade.getEndereco());
        existingUnidade.setComplemento(unidade.getComplemento());
        existingUnidade.setBairro(unidade.getBairro());
        existingUnidade.setCidade(unidade.getCidade());
        existingUnidade.setEstado(unidade.getEstado());
        existingUnidade.setCep(unidade.getCep());

        // Atualize a data de atualização
        existingUnidade.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingUnidade);
    }

    public void delete(Integer id) {
        // Verifique se a unidade existe
        Unidade existingUnidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        // Se não pertence a nenhum colaborador, exclua a unidade
        repository.deleteById(id);
    }
}
