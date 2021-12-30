package com.kseniyamargaretphotography.api.exceptions;

import java.util.Collection;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}