package br.iftm.edu.baoOuNao.api.dto.usuario;

import br.iftm.edu.baoOuNao.domain.model.usuario.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCadastroDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String tipo;
    @Email
    private String email;
    @NotNull
    private int limiteDeLike; // Limite 3
    private boolean ativo;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    @NotNull
    private Role role;
}
