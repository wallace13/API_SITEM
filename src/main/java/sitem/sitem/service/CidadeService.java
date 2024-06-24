package sitem.sitem.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sitem.sitem.model.Cidade;
import sitem.sitem.repository.CidadeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository repository;


    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public Cidade findById(Integer id) {
        Optional<Cidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado! Id: " + id));
    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade create(Cidade cidade) {
        cidade.setIdCidade(0);

        LocalDateTime now = LocalDateTime.now();
        cidade.setCreatedAt(now);
        cidade.setUpdatedAt(now);

        return repository.save(cidade);
    }

    public Cidade update(Integer id, @Valid Cidade cidade) {
        // Verifique se a entidade existe antes de tentar atualizar
        Cidade existingCidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrado"));

        // Atualize as propriedades da entidade existente
        existingCidade.setNome(cidade.getNome());
        existingCidade.setEstado(cidade.getEstado());

        // Atualize a data de atualização
        existingCidade.setUpdatedAt(LocalDateTime.now());

        // Persista a entidade atualizada
        return repository.save(existingCidade);
    }

    public void delete(Integer id) {
        // Verifique se Cidade existe
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrado"));

        repository.deleteById(id);
    }
}
