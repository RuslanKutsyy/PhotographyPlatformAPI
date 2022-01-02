package com.kseniyamargaretphotography.api.exceptions;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExceptionDetails {

    private Timestamp timestamp;
    private int status;
    private String message;
    private String path;

    public ExceptionDetails(Timestamp timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
