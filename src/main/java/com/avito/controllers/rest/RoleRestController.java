package com.avito.controllers.rest;

import com.avito.configs.security.AuthProvider;
import com.avito.models.Role;
import com.avito.service.interfaces.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleRestController {
    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final RoleService roleService;

    @GetMapping("/get")
    public Role getRole(Role role) {
        return roleService.findRoleByName(role.getName());
    }
}
