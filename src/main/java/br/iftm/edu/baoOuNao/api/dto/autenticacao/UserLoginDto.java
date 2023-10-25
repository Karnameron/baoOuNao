package br.iftm.edu.baoOuNao.api.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}
