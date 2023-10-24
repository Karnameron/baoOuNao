package br.iftm.edu.baoOuNao.api.mapper;

import br.iftm.edu.baoOuNao.api.model.UserModel;
import br.iftm.edu.baoOuNao.api.model.input.UserInputModel;
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
    public UserModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, UserModel.class);
    }
    public Usuario toEntity(UserInputModel userInputModel){
        return modelMapper.map(userInputModel, Usuario.class);
    }
    public List<UserModel> toCollectionModel(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
