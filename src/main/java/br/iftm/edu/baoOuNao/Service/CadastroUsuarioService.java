package br.iftm.edu.baoOuNao.Service;


import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeEmUsoException;
import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeNaoEncontradaException;
import br.iftm.edu.baoOuNao.Model.Usuario;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
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
}
