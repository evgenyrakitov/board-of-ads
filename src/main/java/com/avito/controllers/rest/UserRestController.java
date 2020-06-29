package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/admin")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;


    @PutMapping("/add")
    public ResponseEntity<User> create(User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/edit")
    public ResponseEntity<User> update(User user) {
        return ResponseEntity.ok(userService.updateUser(user));
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
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/posting")
    public Set<Posting> getFavoritePostings() {
        return getUser().getFavoritePostings();
    }

    @PostMapping("/posting/add")
    public Posting addFavoritePosting(Long id) {
        Posting posting = userService.findPostingById(id);
        User user = getUser();
        user.addFavoritePosting(posting);
        userService.updateUser(user);
        return posting;
    }

    @PostMapping("/posting/delete")
    public Posting deleteFavoritePosting(Long id) {
        Posting posting = userService.findPostingById(id);
        User user = getUser();
        getUser().getFavoritePostings();
        user.deleteFavoritePosting(posting);
        userService.updateUser(user);
        return posting;
    }

    private User getUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
