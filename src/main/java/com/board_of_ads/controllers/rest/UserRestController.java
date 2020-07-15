package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.service.interfaces.EmailService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final RoleService roleService;
    private final MessageSource messages;
    private final EmailService emailService;
    private final Environment env;

    @CrossOrigin()  //далее - поправить, сделано чтобы работала страничка
    @ApiOperation(value = "create new User", code = 201, response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully create user")})
    @PostMapping(value = "/admin/add"/*, consumes = {"application/json"}*/) //согласно рекомендациям госкомстандарта - создание это post, not put. fixed
    public ResponseEntity<User> create(User user) {
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findRoleByName("USER");
        roleSet.add(role);
        user.setRoles(roleSet);
        userService.addUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);

        return responseEntity;
    }


    @PutMapping("/admin/edit")   // post -> put
    public User update(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/user/favoritePostings/add")
    public void addFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.addFavoritePosting(id, user.getId());
    }

    @PostMapping("/user/favoritePostings/delete")
    public void deleteFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteFavoritePosting(id, user.getId());
    }

    @PostMapping("/user/favoritePostings/delete/all")
    public void deleteAllFavoritePostings(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteAllFavoritePosting(user.getId());
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@RequestParam("email") String userEmail,
                                                 Locale locale){
        User user = userService.findUserByEmail(userEmail);
        boolean boll = (user != null);
        if (boll) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            String subject = messages.getMessage("reset_password.message.reset_password", null, locale);
            String body = messages.getMessage("reset_password.message.reset_password", null, locale) +
                    " \r\n http://" +
                    Objects.requireNonNull(env.getProperty("server.domain")) + ":" +
                    Objects.requireNonNull(env.getProperty("server.port")) +
                    "/reset/changePassword?token=" +
                    token;
            emailService.sendMail(subject, body, user);
        }
        return new ResponseEntity<>(boll, HttpStatus.OK);
    }

}
