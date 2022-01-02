package com.kseniyamargaretphotography.api.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kseniyamargaretphotography.api.DTO.UserRegistrationDTO;
import com.kseniyamargaretphotography.api.enums.UserRole;
import com.kseniyamargaretphotography.api.exceptions.ConflictException;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserNameService userNameService;
    private final RoleService roleService;
    private final PasswordService passwordService;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        User user = findByUserName(username);
        Password password = user.getPasswords().stream().filter(pass -> pass.getEndDate() == null).findFirst().orElse(null);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(username, password.getPassword(), authorities);
    }

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
    public void register(UserRegistrationDTO userRegistrationDto) {
        if (userNameService.findByUserName(userRegistrationDto.getUserName()) != null) {
            throw new ConflictException(messageSource.getMessage("user.by.user.name.conflict", new String[]{userRegistrationDto.getUserName()}, LocaleContextHolder.getLocale()));
        }

        boolean rolesAreValid = UserRole.valuesAsStrings().containsAll(userRegistrationDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (!rolesAreValid) {
            //TODO:change to more appropriate exception
            throw new IllegalArgumentException("Invalid roles");
        }

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User userToCreate = mapper.convertValue(userRegistrationDto, User.class);

        Collection<Role> roles = roleService.findRolesByRoleNames(userRegistrationDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (roles.isEmpty()) {
            //TODO: remove this later when everything about roles is ready
            roles.addAll(Arrays.asList(
                    new Role(null, UserRole.ADMIN.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.DEVELOPER.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.USER.toString(), false, false, true, new ArrayList<>())));
        }

        userToCreate.setRoles(roles);

        save(userToCreate);

        Password password = new Password(passwordEncoder.encode(userRegistrationDto.getPassword()), userToCreate);
        passwordService.saveOrUpdate(password);

        UserName userName = new UserName(userRegistrationDto.getUserName(), userToCreate);
        userNameService.saveOrUpdate(userName);

        userToCreate.setPasswords(Collections.singletonList(password));
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
            throw new NotFoundException(messageSource.getMessage("default.not.found", new Object[]{User.class.getSimpleName(), id}, LocaleContextHolder.getLocale()));
        }

        userRepository.delete(user);
    }

    @Override
    public void addUserRole(Long userId, Long roleId) {
        //TODO: this code has not been tested yet.
        User user = findById(userId);
        Role role = roleService.findById(roleId);
        user.getRoles().add(role);
    }
}