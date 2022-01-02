package com.kseniyamargaretphotography.api.controllers;

import com.kseniyamargaretphotography.api.DTO.UserRegistrationDTO;
import com.kseniyamargaretphotography.api.exceptions.ValidationFailedException;
import com.kseniyamargaretphotography.api.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    @RequestMapping(value = "/registration", method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException(messageSource.getMessage("default.validation.error", null, LocaleContextHolder.getLocale()), errors.getFieldErrors());
        }
        userService.register(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}