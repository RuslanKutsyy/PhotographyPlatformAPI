package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userName.userName LIKE ?1")
    Optional<User> findByUserName(String userName);
}
