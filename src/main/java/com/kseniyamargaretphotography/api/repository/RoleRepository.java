package com.kseniyamargaretphotography.api.repository;

import com.kseniyamargaretphotography.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r from Role r where r.name = ?1")
    Optional<Role> findByRoleName(String roleName);

    @Query("SELECT r FROM Role r WHERE UPPER(r.name) IN :roleNames")
    List<Role> findRolesByRoleNames(Collection<String> roleNames);
}
