package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;

@Data
@AllArgsConstructor
public class ExceptionDetails {

    private Timestamp timestamp;
    private int status;
    private String message;
    private String path;
    private Collection<ErrorDetails> errors;
}
