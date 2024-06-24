package sitem.sitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sitem.sitem.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
