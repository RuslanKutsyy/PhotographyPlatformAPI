package com.kseniyamargaretphotography.api.controllers;

import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.models.UserName;
import com.kseniyamargaretphotography.api.repository.RoleRepository;
import com.kseniyamargaretphotography.api.repository.UserNameRepository;
import com.kseniyamargaretphotography.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final UserRepository userRepository;
    private final UserNameRepository userNameRepository;
    private final RoleRepository roleRepository;

    @RequestMapping(value = "/getUser", method = GET, produces = APPLICATION_JSON_VALUE)
    public User index() {
        UserName userName = new UserName(null, "somePassword1", null, new Timestamp(new Date().getTime()), null);
        userNameRepository.save(userName);
        Role admin = new Role(null, "Admin", true, true, true, null);
        roleRepository.save(admin);
        User userRuslan = new User();
        userRuslan.setFirstName("Ruslan");
        userRuslan.setLastName("Kutsyy");
        userRuslan.setUserName(userName);
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        userRuslan.setRoles(roles);

        userRuslan = userRepository.save(userRuslan);

        return userRuslan;
    }
}