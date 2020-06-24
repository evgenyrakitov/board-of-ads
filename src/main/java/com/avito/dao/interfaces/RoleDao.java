package com.avito.dao.interfaces;

import com.avito.models.Role;


public interface RoleDao {
    Role findRoleByName(String name);

    void addRole(Role role);
}
