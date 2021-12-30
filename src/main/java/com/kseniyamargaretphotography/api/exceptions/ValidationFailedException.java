package com.kseniyamargaretphotography.api.exceptions;

import org.springframework.validation.FieldError;

import java.util.Collection;

public class ValidationFailedException extends ApiException {

    private Collection<FieldError> errors;

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(String message, Collection<FieldError> errors) {
        super(message);
        this.errors = errors;
    }

    public Collection<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<FieldError> errors) {
        this.errors = errors;
    }
}
