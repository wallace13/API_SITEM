package sitem.sitem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "malotes")
@EqualsAndHashCode
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

    public Malote() {
        super();
    }

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
