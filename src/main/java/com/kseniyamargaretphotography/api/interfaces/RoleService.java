package com.kseniyamargaretphotography.api.interfaces;

import com.kseniyamargaretphotography.api.models.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    Role findById(Long roleId);

    Role findByRoleName(String roleName);

    List<Role> findRolesByRoleNames(Collection<String> roleNames);

    List<Role> getAll();
}
