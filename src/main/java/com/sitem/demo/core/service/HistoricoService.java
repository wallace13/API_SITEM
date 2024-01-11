package com.sitem.demo.core.service;

import com.sitem.demo.core.model.Historico;
import com.sitem.demo.core.repository.HistoricoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public Historico findById(Integer id) {
        Optional<Historico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Historico> findAll() {
        return repository.findAll();
    }

    public Historico create(Historico Historico) {
        Historico.setIdHistorico(0);

        LocalDateTime now = LocalDateTime.now();
        Historico.setCreatedAt(now);
        Historico.setUpdatedAt(now);

        return repository.save(Historico);
    }

    public Historico update(Integer id, @Valid Historico Historico) {
        // Verifique se a entidade existe antes de tentar atualizar
        Historico existingHistorico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historico não encontrado"));

        // Atualize as propriedades da entidade existente
        existingHistorico.setDocumento(Historico.getDocumento());
        existingHistorico.setColaborador(Historico.getColaborador());
        existingHistorico.setDtEntrada(Historico.getDtEntrada());
        existingHistorico.setHrEntrada(Historico.getHrEntrada());
        existingHistorico.setDtSaida(Historico.getDtSaida());
        existingHistorico.setHrSaida(Historico.getHrSaida());
        existingHistorico.setStatus(Historico.getStatus());
        existingHistorico.setColDestino(Historico.getColDestino());
        existingHistorico.setUniDestino(Historico.getUniDestino());

        // Atualize a data de atualização
        existingHistorico.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingHistorico);
    }

    public void delete(Integer id) {
        // Verifique se o Historico existe
        Historico existingHistorico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historico não encontrado"));

        repository.deleteById(id);
    }
}
