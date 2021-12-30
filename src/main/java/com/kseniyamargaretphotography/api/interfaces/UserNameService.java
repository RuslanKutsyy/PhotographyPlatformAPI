package com.kseniyamargaretphotography.api.interfaces;

import com.kseniyamargaretphotography.api.models.UserName;

public interface UserNameService {

    UserName findById(Long id);

    UserName findByUserId(Long userId);

    UserName findByUserName(String userName);
}
