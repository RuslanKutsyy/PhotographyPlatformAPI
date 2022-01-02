package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;

import java.sql.Timestamp;
import java.util.Collection;


public class ValidationExceptionDetails extends ExceptionDetails {

    private Collection<ErrorDetails> errors;

    public ValidationExceptionDetails(Timestamp timestamp, int status, String message, String path, Collection<ErrorDetails> errors) {
        super(timestamp, status, message, path);
        this.errors = errors;
    }

    public Collection<ErrorDetails> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ErrorDetails> errors) {
        this.errors = errors;
    }
}
