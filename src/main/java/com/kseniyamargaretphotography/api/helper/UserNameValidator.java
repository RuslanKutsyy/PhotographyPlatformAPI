package com.kseniyamargaretphotography.api.helper;

import com.kseniyamargaretphotography.api.annotations.UserNameValidation;
import lombok.Data;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class UserNameValidator implements ConstraintValidator<UserNameValidation, Object> {

    private final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    @Override
    public boolean isValid(Object userName, ConstraintValidatorContext validatorContext) {
        Matcher matcher = pattern.matcher(userName == null ? "" : userName.toString());
        return matcher.matches();
    }


}
