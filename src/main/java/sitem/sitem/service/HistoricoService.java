package sitem.sitem.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Historico;
import sitem.sitem.repository.HistoricoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    private final HistoricoRepository repository;

    public HistoricoService(HistoricoRepository repository) {
        this.repository = repository;
    }

    public Historico findById(Integer id) {
        Optional<Historico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Historico> findAll() {
        return repository.findAll();
    }

    public Historico create(Historico historico) {
        historico.setIdHistorico(0);

        LocalDateTime now = LocalDateTime.now();
        historico.setCreatedAt(now);
        historico.setUpdatedAt(now);

        return repository.save(historico);
    }

    public Historico update(Integer id, @Valid Historico historico) {
        // Verifique se a entidade existe antes de tentar atualizar
        Historico existingHistorico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historico não encontrado"));

        // Atualize as propriedades da entidade existente
        existingHistorico.setDocumento(historico.getDocumento());
        existingHistorico.setColaborador(historico.getColaborador());
        existingHistorico.setDtEntrada(historico.getDtEntrada());
        existingHistorico.setHrEntrada(historico.getHrEntrada());
        existingHistorico.setDtSaida(historico.getDtSaida());
        existingHistorico.setHrSaida(historico.getHrSaida());
        existingHistorico.setStatus(historico.getStatus());
        existingHistorico.setColDestino(historico.getColDestino());
        existingHistorico.setUniDestino(historico.getUniDestino());

        // Atualize a data de atualização
        existingHistorico.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingHistorico);
    }

    public void delete(Integer id) {
        // Verifique se o Historico existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historico não encontrado"));

        repository.deleteById(id);
    }
}
