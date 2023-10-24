package br.iftm.edu.baoOuNao.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Categoria categoria;
    private int qtdlikes;
    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario user_id;

}
