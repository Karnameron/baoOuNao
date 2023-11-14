package br.iftm.edu.baoOuNao.api.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConsultaDto {
    //private Long id;
    private String nome;
    private String sobrenome;
    private String tipo;
    private String email;
    private String role;
    //private int limiteDeLike; // Limite 3
    //private boolean ativo;
}
