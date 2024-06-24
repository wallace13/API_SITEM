package sitem.sitem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "unidades")
@AllArgsConstructor
@EqualsAndHashCode
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidade;
    private String nome;
    private String endereco;
    private String complemento;
    private String bairro;

    @OneToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @OneToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    private String cep;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Unidade() {
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
