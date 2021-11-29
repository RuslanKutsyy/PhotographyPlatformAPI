package com.kseniyamargaretphotography.api.repositories;

import com.kseniyamargaretphotography.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
