package br.iftm.edu.baoOuNao.Controller;


import br.iftm.edu.baoOuNao.api.dto.usuario.UserCadastroDto;
import br.iftm.edu.baoOuNao.api.dto.usuario.UserConsultaDto;
import br.iftm.edu.baoOuNao.api.mapper.UserMapper;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import br.iftm.edu.baoOuNao.Service.CadastroUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class
UsuarioController {
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserConsultaDto> buscar(){

        var usuarios =  usuarioRepository.findAll();
        return userMapper.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UserConsultaDto buscarPorId(@PathVariable Long usuarioId){
        var usuario = usuarioRepository.getReferenceById(usuarioId);
        return userMapper.toModelCadastro(usuario);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar(@RequestBody @Valid UserCadastroDto inputUsuario){
        var usuario = userMapper.toEntityCadastro(inputUsuario);
        return cadastroUsuarioService.salvar(usuario);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId){
        cadastroUsuarioService.remover(usuarioId);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId,@RequestBody @Valid UserCadastroDto usuario){
       return cadastroUsuarioService.atualizar(usuarioId,usuario);
    }

    @PatchMapping("/{usuarioId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long usuarioId, @RequestBody Map<String, Object> campos) {
       return cadastroUsuarioService.atualizarParcial(usuarioId,campos);
    }
}
