package com.kseniyamargaretphotography.api.controllers;

import com.kseniyamargaretphotography.api.DTO.UserLoginDTO;
import com.kseniyamargaretphotography.api.exceptions.ValidationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MessageSource messageSource;

//    @PostMapping("/login")
//    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO, Errors errors) {
//        if (errors.hasErrors()) {
//            throw new ValidationFailedException(messageSource.getMessage("invalid.credentials", null, LocaleContextHolder.getLocale()), errors.getFieldErrors());
//        }
//
//    }
}
