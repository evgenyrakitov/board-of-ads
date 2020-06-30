package com.avito.service.interfaces;

import com.avito.models.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByName(String name);

    void addRole(Role role);

    List<Role> getAllRoles();
}
