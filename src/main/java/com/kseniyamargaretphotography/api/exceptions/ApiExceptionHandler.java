package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<?> handleValidationFailedException(WebRequest request, ValidationFailedException ex) {
        Collection<ErrorDetails> errorDetails = ex.getErrors().stream().map(error -> new ValidationErrorDetails(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        ExceptionDetails exceptionDetails = new ValidationExceptionDetails(Timestamp.from(Instant.now()), BAD_REQUEST.value(), ex.getLocalizedMessage(), request.getDescription(false).substring(4), errorDetails);
        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(WebRequest request, ConflictException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(Timestamp.from(Instant.now()), CONFLICT.value(), ex.getLocalizedMessage(), request.getDescription(false).substring(4));
        return new ResponseEntity<>(exceptionDetails, CONFLICT);
    }
}
