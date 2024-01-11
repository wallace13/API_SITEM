package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Conteudo;
import com.sitem.demo.core.repository.ConteudoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConteudoService {

    @Autowired
    private ConteudoRepository repository;


    public Conteudo findById(Integer id) {
        Optional<Conteudo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Conteudo> findAll() {
        return repository.findAll();
    }

    public Conteudo create(Conteudo Conteudo) {
        Conteudo.setIdConteudo(0);

        LocalDateTime now = LocalDateTime.now();
        Conteudo.setCreatedAt(now);
        Conteudo.setUpdatedAt(now);

        return repository.save(Conteudo);
    }

    public Conteudo update(Integer id, @Valid Conteudo Conteudo) {
        // Verifique se a entidade existe antes de tentar atualizar
        Conteudo existingConteudo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteudo não encontrado"));

        // Atualize as propriedades da entidade existente
        existingConteudo.setDocumento(Conteudo.getDocumento());
        existingConteudo.setMalote(Conteudo.getMalote());
        existingConteudo.setDtEntrada(Conteudo.getDtEntrada());
        existingConteudo.setHrEntrada(Conteudo.getHrEntrada());
        existingConteudo.setDtSaida(Conteudo.getDtSaida());
        existingConteudo.setHrSaida(Conteudo.getHrSaida());

        // Atualize a data de atualização
        existingConteudo.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingConteudo);
    }

    public void delete(Integer id) {
        // Verifique se a Conteudo existe
        Conteudo existingConteudo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteudo não encontrado"));

        repository.deleteById(id);
    }
}
