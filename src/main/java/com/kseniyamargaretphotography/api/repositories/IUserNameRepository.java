package com.kseniyamargaretphotography.api.repositories;

import com.kseniyamargaretphotography.api.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserNameRepository extends JpaRepository<UserName, Long> {
}
