package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PutMapping("/add")
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    public User update(User user) {
        User userUpdate = userService.findUserByLogin(user.getUsername());
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userService.updateUser(userUpdate);
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

/*
    @GetMapping("/all")
    public ModelAndView list() {
        List<User> allUsers = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("????????"); //надо добавить вьюшку
        modelAndView.getModelMap().addAttribute("listUsers", allUsers);
        return modelAndView;
    }
*/

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
