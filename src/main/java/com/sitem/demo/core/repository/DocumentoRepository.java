package com.sitem.demo.core.repository;

import com.sitem.demo.core.model.Malote;
import com.sitem.demo.core.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentoRepository  extends JpaRepository<Documento, Integer> {
}
