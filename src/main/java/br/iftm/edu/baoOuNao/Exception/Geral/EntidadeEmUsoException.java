package br.iftm.edu.baoOuNao.Exception.Geral;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entidade em uso")
public class EntidadeEmUsoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
