package br.com.brenootsuka.pegcontas.handler;

import br.com.brenootsuka.pegcontas.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class MethodArgumentNotValidHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            MethodArgumentNotValidException exception
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();

        Set<String> messages = new HashSet<>(errors.size());
        messages.addAll(errors.stream()
                .map(error -> String.format(
                        "Argument Not Valid: %s %s",
                        ((FieldError) error).getField(),
                        error.getDefaultMessage()
                ))
                .collect(Collectors.toList()));

        ExceptionResponse response;

        response = new ExceptionResponse(
                ZonedDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                messages
        );

        return new ResponseEntity<>(response, status);
    }
}
