package sitem.sitem.service;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Conteudo;
import sitem.sitem.repository.ConteudoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConteudoService {

    private final ConteudoRepository repository;

    public ConteudoService(ConteudoRepository repository) {
        this.repository = repository;
    }

    public Conteudo findById(Integer id) {
        Optional<Conteudo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Conteudo> findAll() {
        return repository.findAll();
    }

    public Conteudo create(Conteudo conteudo) {
        conteudo.setIdConteudo(0);

        LocalDateTime now = LocalDateTime.now();
        conteudo.setCreatedAt(now);
        conteudo.setUpdatedAt(now);

        return repository.save(conteudo);
    }

    public Conteudo update(Integer id, @Valid Conteudo conteudo) {
        // Verifique se a entidade existe antes de tentar atualizar
        Conteudo existingConteudo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteudo não encontrado"));

        // Atualize as propriedades da entidade existente
        existingConteudo.setDocumento(conteudo.getDocumento());
        existingConteudo.setMalote(conteudo.getMalote());
        existingConteudo.setDtEntrada(conteudo.getDtEntrada());
        existingConteudo.setHrEntrada(conteudo.getHrEntrada());
        existingConteudo.setDtSaida(conteudo.getDtSaida());
        existingConteudo.setHrSaida(conteudo.getHrSaida());

        // Atualize a data de atualização
        existingConteudo.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingConteudo);
    }

    public void delete(Integer id) {
        // Verifique se a Conteudo existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteudo não encontrado"));

        repository.deleteById(id);
    }
}
