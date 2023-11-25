package br.iftm.edu.baoOuNao.api.dto.proposta;

import br.iftm.edu.baoOuNao.domain.model.proposta.Situacao;
import lombok.Data;

@Data
public class PropostaFeedbackDto {
    private Situacao situacao;
    private String feedback;
}
