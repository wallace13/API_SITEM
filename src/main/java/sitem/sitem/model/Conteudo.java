package sitem.sitem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conteudos")
@AllArgsConstructor
@EqualsAndHashCode
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConteudo;
    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;
    @OneToOne
    @JoinColumn(name = "malote_id")
    private Malote malote;
    private LocalDateTime dtEntrada;
    private LocalDateTime dtSaida;
    private LocalDateTime hrEntrada;
    private LocalDateTime hrSaida;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Conteudo() {
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
