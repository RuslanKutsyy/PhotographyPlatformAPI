package com.kseniyamargaretphotography.api.controllers;

import com.kseniyamargaretphotography.api.repositories.IRoleRepository;
import com.kseniyamargaretphotography.api.repositories.IUserNameRepository;
import com.kseniyamargaretphotography.api.repositories.IUserRepository;
import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.models.UserName;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    IUserRepository userRepository;
    IRoleRepository roleRepository;
    IUserNameRepository userNameRepository;

    @RequestMapping(value = "/getUser", method = GET, produces = APPLICATION_JSON_VALUE)
    public User index() {
        UserName userName = new UserName(null, "somePassword1", null, new Timestamp(new Date().getTime()), null);
        userNameRepository.save(userName);
        Role admin = new Role(null, "Admin", true, true, true, null);
        roleRepository.save(admin);
        User userRuslan = new User();
        userRuslan.setFirstName("Ruslan");
        userRuslan.setLastName("Kutsyy");
        userRuslan.setUserNameId(userName);
        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        userRuslan.setRole(roles);

        userRuslan = userRepository.save(userRuslan);

        return userRuslan;
    }
}