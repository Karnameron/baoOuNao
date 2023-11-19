package br.iftm.edu.baoOuNao.api.dto.usuario;

import br.iftm.edu.baoOuNao.domain.model.usuario.Role;
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
    private Role role;
    //private int limiteDeLike; // Limite 3
    private boolean ativo;
}
