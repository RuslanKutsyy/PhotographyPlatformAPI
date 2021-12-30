package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<?> validationFailedException(HttpServletRequest request, ValidationFailedException ex) {
        Collection<ErrorDetails> errorDetails = ex.getErrors().stream().map(error -> new ValidationErrorDetails(error.getField(), messageSource.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale()))).collect(Collectors.toList());
        ExceptionDetails exceptionDetails = new ExceptionDetails(Timestamp.from(Instant.now()), BAD_REQUEST.value(), ex.getMessage(), request.getServletPath(), errorDetails);
        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

}
