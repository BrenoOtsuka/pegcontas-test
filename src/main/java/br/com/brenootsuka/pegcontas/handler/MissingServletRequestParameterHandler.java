package br.com.brenootsuka.pegcontas.handler;

import br.com.brenootsuka.pegcontas.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class MissingServletRequestParameterHandler {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    protected ResponseEntity<Object> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = String.format(
                "Missing parameter: '%s' parameter is not present",
                exception.getParameterName()
        );
        Set<String> messages = new HashSet<>(1);
        messages.add(message);

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
