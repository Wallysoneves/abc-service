package br.com.abc.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbcException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorModel> handleCustomException(AbcException ex) {

        ErrorModel apiErrorModel = ErrorModel.builder()
                .status(ex.getStatus())
                .erro(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiErrorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AbcException.class)
    public ResponseEntity<ErrorModel> handleBadRequestException(AbcException ex) {

        ErrorModel apiErrorModel = ErrorModel.builder()
                .status(ex.getStatus())
                .erro(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiErrorModel);
    }
}
