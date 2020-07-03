package com.avito.controllers.rest;

import com.avito.models.Role;
import com.avito.models.User;
import com.avito.service.interfaces.RoleService;
import com.avito.service.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/admin")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final RoleService roleService;

    @CrossOrigin()  //далее - поправить, сделано чтобы работала страничка
    @ApiOperation(value = "create new User", code = 201, response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully create user")})
    @PostMapping(value = "/add"/*, consumes = {"application/json"}*/) //согласно рекомендациям госкомстандарта - создание это post, not put. fixed
    public ResponseEntity<User> create(User user) {
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findRoleByName("USER");
        roleSet.add(role);
        user.setRoles(roleSet);
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping("/edit")   // post -> put
    public User update(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/favoritePostings/add")
    public ResponseEntity<User> addFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.addFavoritePosting(id, user.getId()));
    }

    @PostMapping("/favoritePostings/delete")
    public void deleteFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteFavoritePosting(id, user.getId());
    }

}
