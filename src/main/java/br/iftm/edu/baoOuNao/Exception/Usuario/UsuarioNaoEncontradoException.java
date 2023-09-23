package br.iftm.edu.baoOuNao.Exception.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entidade não encontrada")
public class UsuarioNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
