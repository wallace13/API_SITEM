package sitem.sitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sitem.sitem.model.Documento;

public interface DocumentoRepository  extends JpaRepository<Documento, Integer> {
}
