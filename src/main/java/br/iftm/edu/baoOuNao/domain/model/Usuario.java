package br.iftm.edu.baoOuNao.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String sobrenome;
    @NotNull
    private String tipo;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String senha;
    private int limiteDeLike; // Limite 3
    private boolean statusProposta;
}
