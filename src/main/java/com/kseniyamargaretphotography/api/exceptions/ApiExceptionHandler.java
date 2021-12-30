package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<?> handleValidationFailedException(WebRequest request, ValidationFailedException ex) {
        Collection<ErrorDetails> errorDetails = ex.getErrors().stream().map(error -> new ValidationErrorDetails(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        ExceptionDetails exceptionDetails = new ExceptionDetails(Timestamp.from(Instant.now()), BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false).substring(4), errorDetails);
        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

//    public ResponseEntity<?> handleInternalServerErrorException(Ht)
}
