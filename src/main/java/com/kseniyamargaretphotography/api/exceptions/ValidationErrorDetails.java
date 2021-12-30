package com.kseniyamargaretphotography.api.exceptions;

import com.kseniyamargaretphotography.api.interfaces.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDetails implements ErrorDetails {
    private String field;
    private String message;
}
