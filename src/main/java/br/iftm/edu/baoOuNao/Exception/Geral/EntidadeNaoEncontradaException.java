package br.iftm.edu.baoOuNao.Exception.Geral;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
public class EntidadeNaoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
