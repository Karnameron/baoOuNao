package br.iftm.edu.baoOuNao.Service;


import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeEmUsoException;
import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeNaoEncontradaException;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import br.iftm.edu.baoOuNao.api.dto.usuario.UserCadastroDto;
import br.iftm.edu.baoOuNao.api.mapper.UserMapper;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class CadastroUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private UserMapper userMapper;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<?> atualizarParcial(Long usuarioId,  Map<String, Object> campos){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);
        if (usuarioAtual.isPresent()) {
            merge(campos, usuarioAtual.get());
            Usuario usuarioSalvo = salvar(usuarioAtual.get());
            return ResponseEntity.ok(usuarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Usuario> atualizar(Long usuarioId, UserCadastroDto usuario){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuarioId);
        if(usuarioAtual.isPresent()){
            BeanUtils.copyProperties(userMapper.toEntityCadastro(usuario), usuarioAtual.get(),"id");
            Usuario usuarioSalvo = salvar(usuarioAtual.get());
            return ResponseEntity.ok(usuarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }
    public void remover(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuário com o código %d", usuarioId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("O usuário com o código %d não pode ser removido porque está em uso", usuarioId));
        }
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
