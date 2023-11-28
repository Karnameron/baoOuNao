package br.iftm.edu.baoOuNao.api.dto.like;

import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaConsultaDto;
import br.iftm.edu.baoOuNao.api.dto.usuario.UserConsultaDto;
import lombok.Data;

@Data
public class ConsultaCurtirDTO {
    UserConsultaDto usuario;
    PropostaConsultaDto proposta;
}
