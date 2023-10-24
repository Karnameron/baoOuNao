package br.iftm.edu.baoOuNao.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputModel {
    private Long id;
    private String nome;
    private String sobrenome;
    private String tipo;
    private String email;
    private String senha;
    private int limiteDeLike; // Limite 3
    private boolean statusProposta;
}
