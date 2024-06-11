package br.com.abc.infrastructure.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbcException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorModel> handleCustomException(AbcException ex) {

        ErrorModel apiErrorModel = ErrorModel.builder()
                .status(ex.getStatus())
                .error(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiErrorModel, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCodeException status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorModel apiErrorModel = ErrorModel.builder()
                .errors(errors)
                .build();

        return handleExceptionInternal(ex, apiErrorModel, headers, apiErrorModel.getStatus(), request);
    }
}
