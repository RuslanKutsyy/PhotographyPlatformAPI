package com.kseniyamargaretphotography.api.services;

import com.kseniyamargaretphotography.api.exceptions.NotFoundException;
import com.kseniyamargaretphotography.api.interfaces.UserNameService;
import com.kseniyamargaretphotography.api.models.UserName;
import com.kseniyamargaretphotography.api.repository.UserNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserNameServiceImpl implements UserNameService {

    private final UserNameRepository userNameRepository;
    private final MessageSource messageSource;

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

    @Override
    public void saveOrUpdate(UserName userName) {
        userNameRepository.save(userName);
    }

    @Override
    public void delete(Long userNameId) {
        UserName userName = userNameRepository.findById(userNameId).orElse(null);

        if (userName == null) {
            throw new NotFoundException(messageSource.getMessage("default.not.found", new Object[]{UserName.class.getSimpleName(), userNameId}, LocaleContextHolder.getLocale()));
        }

        userNameRepository.delete(userName);
    }
}
