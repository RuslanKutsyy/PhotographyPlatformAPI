package com.kseniyamargaretphotography.api.interfaces;

import com.kseniyamargaretphotography.api.models.Password;

import java.util.Collection;

public interface PasswordService {

    public Password findById(Long passwordId);

    public Collection<Password> findAllByUserId(Long userId);

    public Password findUserCurrentPassword(Long userId);

    public void saveOrUpdate(Password password);

    public void delete(Long passwordId);
}
