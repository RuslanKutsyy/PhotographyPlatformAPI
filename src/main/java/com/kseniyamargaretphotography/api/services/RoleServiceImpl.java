package com.kseniyamargaretphotography.api.services;

import com.kseniyamargaretphotography.api.interfaces.RoleService;
import com.kseniyamargaretphotography.api.models.Role;
import com.kseniyamargaretphotography.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
