package com.kseniyamargaretphotography.api.annotations;

import com.kseniyamargaretphotography.api.helper.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserNameValidation {

    public String message() default "default.value.invalid";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};


}
