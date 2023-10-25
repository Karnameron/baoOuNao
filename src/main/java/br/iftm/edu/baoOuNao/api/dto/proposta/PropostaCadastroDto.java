package br.iftm.edu.baoOuNao.api.dto.proposta;

import br.iftm.edu.baoOuNao.domain.model.Categoria;
import br.iftm.edu.baoOuNao.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropostaCadastroDto {

    @NotNull
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private Categoria categoria;
    @NotNull
    private int qtdlikes;
    @NotNull
    private Usuario user_id;
}
