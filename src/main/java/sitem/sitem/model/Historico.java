package sitem.sitem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historicos")
@AllArgsConstructor
@EqualsAndHashCode
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorico;
    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;
    @OneToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;
    private LocalDateTime dtEntrada;
    private LocalDateTime dtSaida;
    private LocalDateTime hrEntrada;
    private LocalDateTime hrSaida;
    private String status;
    private String colDestino;
    private String uniDestino;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Historico() {
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
