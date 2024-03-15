package br.com.abc.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AbcException extends RuntimeException {

    private HttpStatus status;
    private Object retorno;

    public AbcException(String mensagem) {
        super(mensagem);
    }

    public AbcException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.status = status;
    }

    AbcException(String mensagem, HttpStatus status, Object retorno) {
        super(mensagem);
        this.status = status;
        this.retorno = retorno;
    }
}
