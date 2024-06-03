package br.com.abc.infrastructure.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Map<String, String> handleMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Map<String, String> handleException(
            Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return error;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Map<String, String> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ex.getName(), ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UsernameNotFoundException.class})
    public Map<String, String> handleUserNotFoundException(
            UsernameNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleSqlException (DataIntegrityViolationException ex){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleSpecificDataIntegrityViolationException(DataIntegrityViolationException ex){
        // Adicione sua lógica personalizada aqui
        return new ResponseEntity<>("Mensagem personalizada de erro", HttpStatus.CONFLICT);
    }
}
