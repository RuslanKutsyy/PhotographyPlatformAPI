package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserNameRepository extends JpaRepository<UserName, Long> {

    @Query("SELECT u from UserName u where u.user.id = ?1")
    Optional<UserName> findByUserId(Long userId);

    @Query("SELECT u FROM UserName u WHERE u.userName = ?1")
    Optional<UserName> findByUserName(String userName);
}
