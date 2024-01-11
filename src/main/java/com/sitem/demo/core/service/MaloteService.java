package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Colaborador;
import com.sitem.demo.core.model.Documento;
import com.sitem.demo.core.model.Malote;
import com.sitem.demo.core.repository.ColaboradorRepository;
import com.sitem.demo.core.repository.DocumentoRepository;
import com.sitem.demo.core.repository.MaloteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaloteService {

    @Autowired
    private MaloteRepository repository;

    @Autowired
    private DocumentoRepository documentoRepository;

    public Malote findById(Integer id) {
        Optional<Malote> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Malote> findAll() {
        return repository.findAll();
    }

    public Malote create(Malote Malote) {
        Malote.setIdMalote(0);

        LocalDateTime now = LocalDateTime.now();
        Malote.setCreatedAt(now);
        Malote.setUpdatedAt(now);

        return repository.save(Malote);
    }

    public Malote update(Integer id, @Valid Malote Malote) {
        // Verifique se a entidade existe antes de tentar atualizar
        Malote existingMalote = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Malote não encontrada"));

        // Atualize as propriedades da entidade existente
        existingMalote.setIdLacre(Malote.getIdLacre());
        existingMalote.setStatus(Malote.getStatus());
        existingMalote.setDtEntrada(Malote.getDtEntrada());
        existingMalote.setHrEntrada(Malote.getHrEntrada());
        existingMalote.setDtSaida(Malote.getDtSaida());
        existingMalote.setHrSaida(Malote.getHrSaida());

        // Atualize a data de atualização
        existingMalote.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingMalote);
    }

    public void delete(Integer id) {
        // Verifique se o Malote existe
        Malote existingMalote = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Malote não encontrado"));

        // Se não pertence a nenhum colaborador, exclua a Malote
        repository.deleteById(id);
    }
}
