package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.PostingService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/rest/favorites")
@AllArgsConstructor
public class FavoritesRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final PostingService postingService;
    private final UserService userService;


    @GetMapping("/posting")
    public Set<Posting> getFavoritePostings() {
        return getUser().getFavoritePostings();
    }

    @PostMapping("/posting/add")
    public Posting addFavoritePosting(Long id) {
        Posting posting = postingService.findPostingById(id);
        User user = getUser();
        user.addFavoritePosting(posting);
        userService.updateUser(user);
        return posting;
    }

    @PostMapping("/posting/delete")
    public Posting deleteFavoritePosting(Long id) {
        Posting posting = postingService.findPostingById(id);
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
