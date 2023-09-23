package br.iftm.edu.baoOuNao.Controller;


import br.iftm.edu.baoOuNao.Exception.Usuario.UsuarioNaoEncontradoException;
import br.iftm.edu.baoOuNao.Model.Usuario;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import br.iftm.edu.baoOuNao.Service.CadastroUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    @GetMapping
    public List<Usuario> buscar(){
        return usuarioRepository.findAll();
    }


    @GetMapping("/{usuarioId}")
    public Usuario buscarPorId(@PathVariable Long usuarioId){
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(()
                        -> new UsuarioNaoEncontradoException
                        ("Usuário não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar(@RequestBody Usuario usuario){
        return cadastroUsuarioService.salvar(usuario);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId){
        cadastroUsuarioService.remover(usuarioId);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId,@RequestBody Usuario usuario){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);
        if(usuarioAtual.isPresent()){
            BeanUtils.copyProperties(usuario, usuarioAtual.get(),"id");
            Usuario usuarioSalvo = cadastroUsuarioService.salvar(usuarioAtual.get());
            return ResponseEntity.ok(usuarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{usuarioId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long usuarioId, @RequestBody Map<String, Object> campos) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);
        if (usuarioAtual.isPresent()) {
            merge(campos, usuarioAtual.get());
            Usuario usuarioSalvo = cadastroUsuarioService.salvar(usuarioAtual.get());
            return ResponseEntity.ok(usuarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Usuario usuarioDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario usuarioOrigem = objectMapper.convertValue(dadosOrigem, Usuario.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)-> {
            Field field = ReflectionUtils.findField(Usuario.class, nomePropriedade);

            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, usuarioOrigem);
            ReflectionUtils.setField(field, usuarioDestino, novoValor);
        });
    }
}
