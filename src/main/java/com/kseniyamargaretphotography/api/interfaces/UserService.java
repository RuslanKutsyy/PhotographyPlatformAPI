package com.kseniyamargaretphotography.api.interfaces;

import com.kseniyamargaretphotography.api.DTO.UserDTO;
import com.kseniyamargaretphotography.api.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User findByUserName(String userName);

    User save(UserDTO userDto);

    void delete(Long id);

    void delete(User user);
}
