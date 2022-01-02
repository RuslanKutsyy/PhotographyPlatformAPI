package com.kseniyamargaretphotography.api.interfaces;

import com.kseniyamargaretphotography.api.DTO.UserRegistrationDTO;
import com.kseniyamargaretphotography.api.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User findByUserName(String userName);

    void register(UserRegistrationDTO userRegistrationDto);

    void save(User user);

    void delete(Long id);

    void addUserRole(Long userId, Long roleId);
}
