package com.kseniyamargaretphotography.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kseniyamargaretphotography.api.DTO.UserDTO;
import com.kseniyamargaretphotography.api.enums.UserRole;
import com.kseniyamargaretphotography.api.interfaces.RoleService;
import com.kseniyamargaretphotography.api.interfaces.UserNameService;
import com.kseniyamargaretphotography.api.interfaces.UserService;
import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.models.UserName;
import com.kseniyamargaretphotography.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserNameService userNameService;
    private final RoleService roleService;

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
    public User save(UserDTO userDto) {

        if (userNameService.findByUserName(userDto.getUserName()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        boolean rolesAreValid = UserRole.valuesAsStrings().containsAll(userDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (!rolesAreValid) {
            throw new IllegalArgumentException("Invalid roles");
        }

        ObjectMapper mapper = new ObjectMapper();
        User userToCreate = mapper.convertValue(userDto, User.class);

        UserName userName = new UserName();
        userName.setUserName(userDto.getUserName());

        userToCreate.setUserName(userName);

        Collection<Role> roles = roleService.findRolesByRoleNames(userDto.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet()));

        if (roles.isEmpty()) {
            roles.addAll(List.of(
                    new Role(null, UserRole.ADMIN.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.DEVELOPER.toString(), true, true, true, new ArrayList<>()),
                    new Role(null, UserRole.USER.toString(), false, false, true, new ArrayList<>())));
        }

        userToCreate.setRoles(roles);

        return userRepository.save(userToCreate);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(User user) {

    }
}
