package br.iftm.edu.baoOuNao.domain.model.proposta;


import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
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
    private boolean anonimo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private String feedback;
}
