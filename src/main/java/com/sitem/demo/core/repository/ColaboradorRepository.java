package com.sitem.demo.core.repository;

import com.sitem.demo.core.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}