package com.kseniyamargaretphotography.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kseniyamargaretphotography.api.DTO.UserDTO;
import com.kseniyamargaretphotography.api.enums.UserRole;
import com.kseniyamargaretphotography.api.exceptions.NotFoundException;
import com.kseniyamargaretphotography.api.interfaces.PasswordService;
import com.kseniyamargaretphotography.api.interfaces.RoleService;
import com.kseniyamargaretphotography.api.interfaces.UserNameService;
import com.kseniyamargaretphotography.api.interfaces.UserService;
import com.kseniyamargaretphotography.api.models.Password;
import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.models.UserName;
import com.kseniyamargaretphotography.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserNameService userNameService;
    private final RoleService roleService;
    private final PasswordService passwordService;
    private final MessageSource messageSource;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public void register(UserDTO userDto) {
        if (userNameService.findByUserName(userDto.getUserName()) != null) {
            //TODO:change to conflict
            throw new IllegalArgumentException("User already exists");
        }

        boolean rolesAreValid = UserRole.valuesAsStrings().containsAll(userDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (!rolesAreValid) {
            //TODO:change to more appropriate exception
            throw new IllegalArgumentException("Invalid roles");
        }

        ObjectMapper mapper = new ObjectMapper();
        User userToCreate = mapper.convertValue(userDto, User.class);

        Collection<Role> roles = roleService.findRolesByRoleNames(userDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (roles.isEmpty()) {
            //TODO: remove this later when everything about roles is ready
            roles.addAll(List.of(
                    new Role(null, UserRole.ADMIN.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.DEVELOPER.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.USER.toString(), false, false, true, new ArrayList<>())));
        }

        userToCreate.setRoles(roles);

        save(userToCreate);

        Password password = new Password(userDto.getPassword(), userToCreate);
        passwordService.saveOrUpdate(password);

        UserName userName = new UserName(userDto.getUserName(), userToCreate);
        userNameService.saveOrUpdate(userName);

        userToCreate.setPassword(Collections.singletonList(password));
        userToCreate.setUserName(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);

        if (user == null) {
            throw new NotFoundException(messageSource.getMessage("user.not.found", new Long[] {id}, LocaleContextHolder.getLocale()));
        }

        userRepository.delete(user);
    }
}