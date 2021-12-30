package com.kseniyamargaretphotography.api.controllers;

import com.kseniyamargaretphotography.api.DTO.UserDTO;
import com.kseniyamargaretphotography.api.exceptions.ValidationFailedException;
import com.kseniyamargaretphotography.api.interfaces.UserService;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.repository.RoleRepository;
import com.kseniyamargaretphotography.api.repository.UserNameRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
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
    private final UserNameRepository userNameRepository;
    private final RoleRepository roleRepository;
    private final MessageSource messageSource;

    @RequestMapping(value = "/registration", method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult errors) {
        if (errors.hasErrors()) {
//            return new ResponseEntity<Object>(errors.getFieldErrors(), HttpStatus.BAD_REQUEST);
            throw new ValidationFailedException("Validation Failed", errors.getFieldErrors());
        }


        return new ResponseEntity<User>(userService.save(userDTO), HttpStatus.ACCEPTED);
    }
}