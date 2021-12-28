package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNameRepository extends JpaRepository<UserName, Long> {
}
