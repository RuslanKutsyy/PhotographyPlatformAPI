package com.kseniyamargaretphotography.api.services;

import com.kseniyamargaretphotography.api.interfaces.RoleService;
import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElse(null);
    }

    @Override
    public List<Role> findRolesByRoleNames(Collection<String> roleNames) {
        return roleRepository.findRolesByRoleNames(roleNames);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
