package com.kseniyamargaretphotography.api.services;

import com.kseniyamargaretphotography.api.interfaces.UserNameService;
import com.kseniyamargaretphotography.api.models.UserName;
import com.kseniyamargaretphotography.api.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNameServiceImpl implements UserNameService {

    private final UserNameRepository userNameRepository;

    @Autowired
    public UserNameServiceImpl(UserNameRepository userNameRepository) {
        this.userNameRepository = userNameRepository;
    }


    @Override
    public UserName findById(Long id) {
        return userNameRepository.findById(id).orElse(null);
    }

    @Override
    public UserName findByUserId(Long userId) {
        return userNameRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public UserName findByUserName(String userName) {
        return userNameRepository.findByUserName(userName).orElse(null);
    }
}
