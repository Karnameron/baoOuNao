package br.iftm.edu.baoOuNao.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private int qtdlikes;
    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario user_id;

}
