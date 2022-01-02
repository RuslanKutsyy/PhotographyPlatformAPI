package com.kseniyamargaretphotography.api.services;

import com.kseniyamargaretphotography.api.exceptions.NotFoundException;
import com.kseniyamargaretphotography.api.interfaces.PasswordService;
import com.kseniyamargaretphotography.api.models.Password;
import com.kseniyamargaretphotography.api.models.User;
import com.kseniyamargaretphotography.api.repository.PasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepository passwordRepository;
    private final MessageSource messageSource;

    @Override
    public Password findById(Long passwordId) {
        return passwordRepository.findById(passwordId).orElse(null);
    }

    @Override
    public Collection<Password> findAllByUserId(Long userId) {
        return passwordRepository.findAllByUserId(userId);
    }

    @Override
    public Password findUserCurrentPassword(Long userId) {
        return passwordRepository.findUserCurrentPassword(userId).orElse(null);
    }

    @Override
    public void saveOrUpdate(Password password) {
        passwordRepository.save(password);
    }

    @Override
    public void delete(Long passwordId) {
        Password passwordToDelete = findById(passwordId);

        if (passwordToDelete == null) {
            throw new NotFoundException(messageSource.getMessage("default.not.found", new Object[]{Password.class.getSimpleName(), passwordId}, LocaleContextHolder.getLocale()));
        }

        passwordRepository.delete(passwordToDelete);
    }
}
