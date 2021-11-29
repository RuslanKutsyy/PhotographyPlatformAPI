package com.kseniyamargaretphotography.api.repositories;

import com.kseniyamargaretphotography.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
