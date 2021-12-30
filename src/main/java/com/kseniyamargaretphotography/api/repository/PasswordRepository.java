package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("SELECT p FROM Password p WHERE p.user.id = ?1")
    Collection<Password> findAllByUserId(Long userId);

    @Query("SELECT p FROM Password p WHERE p.user.id = ?1 AND p.endDate IS NULL")
    Password findUserCurrentPassword(Long userId);
}
