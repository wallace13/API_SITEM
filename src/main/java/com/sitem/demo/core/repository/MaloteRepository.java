package com.sitem.demo.core.repository;

import com.sitem.demo.core.model.Colaborador;
import com.sitem.demo.core.model.Malote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaloteRepository extends JpaRepository<Malote, Integer> {

}
