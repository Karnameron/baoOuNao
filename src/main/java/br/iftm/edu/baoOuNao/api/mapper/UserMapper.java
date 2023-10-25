package br.iftm.edu.baoOuNao.api.mapper;

import br.iftm.edu.baoOuNao.api.dto.autenticacao.TokenJwtDto;
import br.iftm.edu.baoOuNao.api.dto.usuario.UserConsultaDto;
import br.iftm.edu.baoOuNao.api.dto.usuario.UserCadastroDto;
import br.iftm.edu.baoOuNao.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {
    private ModelMapper modelMapper;
    public UserConsultaDto toModelCadastro(Usuario usuario) {
        return modelMapper.map(usuario, UserConsultaDto.class);
    }
    public Usuario toEntityCadastro(UserCadastroDto userCadastroDto){
        return modelMapper.map(userCadastroDto, Usuario.class);
    }
    public List<UserConsultaDto> toCollectionModel(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toModelCadastro)
                .collect(Collectors.toList());
    }




}
