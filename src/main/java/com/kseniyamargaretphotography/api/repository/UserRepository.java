package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
