package com.sitem.demo.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "malotes")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Malote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMalote;
    private Integer idLacre;
    private String status;
    private LocalDateTime dtEntrada;
    private LocalDateTime dtSaida;
    private LocalDateTime hrEntrada;
    private LocalDateTime hrSaida;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        var now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
