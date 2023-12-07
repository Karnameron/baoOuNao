package br.iftm.edu.baoOuNao.api.dto.proposta;

import br.iftm.edu.baoOuNao.api.dto.usuario.UserConsultaDto;
import br.iftm.edu.baoOuNao.domain.model.proposta.Categoria;
import br.iftm.edu.baoOuNao.domain.model.proposta.Situacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropostaConsultaDto {
    @NotNull
    private Long id;
    @NotNull
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private Categoria categoria;
    @NotNull
    private boolean anonimo;
    @NotNull
    private UserConsultaDto usuario;
    @NotNull
    private int qtdLikes;
    @NotNull
    private Situacao situacao;
    @NotBlank
    private String feedback;
}