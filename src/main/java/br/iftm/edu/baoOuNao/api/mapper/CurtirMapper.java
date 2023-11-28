package br.iftm.edu.baoOuNao.api.mapper;

import br.iftm.edu.baoOuNao.api.dto.like.ConsultaCurtirDTO;
import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CurtirMapper {
    private ModelMapper modelMapper;

    public ConsultaCurtirDTO toConsultaLikeDTO(Curtir curtir){
      return  modelMapper.map(curtir, ConsultaCurtirDTO.class);
    }
}
