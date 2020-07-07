package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Role;
import com.board_of_ads.service.interfaces.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/role")
public class RoleRestController {
    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final RoleService roleService;

    @GetMapping("/get")
    public ResponseEntity<Role> getRole(Role role) {
        return ResponseEntity.ok(roleService.findRoleByName(role.getName()));
    }
}
