package com.avito.service.interfaces;

import com.avito.models.Role;

public interface RoleService {

    Role findRoleByName(String name);

    void addRole(Role role);

}
