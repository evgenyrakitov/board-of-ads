package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByName(String name);

    void addRole(Role role);

    List<Role> getAllRoles();
}
