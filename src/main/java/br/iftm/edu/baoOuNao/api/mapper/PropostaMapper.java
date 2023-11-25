package br.iftm.edu.baoOuNao.api.mapper;

import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaCadastroDto;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaConsultaDto;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaFeedbackDto;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PropostaMapper {

    private ModelMapper modelMapper;

    public PropostaConsultaDto toModelConsulta(Proposta proposta){
        return modelMapper.map(proposta, PropostaConsultaDto.class);
    }

    public Proposta toEntity(PropostaCadastroDto propostaCadastroDto){
        return modelMapper.map(propostaCadastroDto,Proposta.class);
    }

    public List<PropostaConsultaDto> toCollectionModel(List<Proposta> propostas){
        return propostas.stream()
                .map(this::toModelConsulta)
                .collect(Collectors.toList());
    }

    public PropostaFeedbackDto toFeedback(Proposta proposta){
        return modelMapper.map(proposta,PropostaFeedbackDto.class);
    }
}