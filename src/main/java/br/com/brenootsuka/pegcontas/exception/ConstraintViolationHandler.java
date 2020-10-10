package br.com.brenootsuka.pegcontas.exception;

import br.com.brenootsuka.pegcontas.model.response.ConstraintViolationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ConstraintViolationHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        Set<ConstraintViolation<?>> constraintViolations;

        constraintViolations = exception.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format(
                        "Constraint violation: %s %s",
                        constraintViolation.getPropertyPath(),
                        constraintViolation.getMessage()
                ))
                .collect(Collectors.toList()));

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ConstraintViolationResponse response;

        response = new ConstraintViolationResponse(
                ZonedDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                messages
        );

        return new ResponseEntity<>(response, status);
    }
}
