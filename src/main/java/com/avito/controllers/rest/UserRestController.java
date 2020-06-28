package com.avito.controllers.rest;

import com.avito.models.Role;
import com.avito.models.User;
import com.avito.service.interfaces.RoleService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final RoleService roleService;


    @PutMapping("/add")
    public User create(User user) {
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findRoleByName("USER");
        roles.add(role);
        user.setRoles(roles);
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    public User update(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    // На случай если надо будет получить пользователя по ИД
//    @GetMapping("/get/{id}")
//    public User getUser(@PathVariable("id") String id) {
//       return  userService.findById(id);
//    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
