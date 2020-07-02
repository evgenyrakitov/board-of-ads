package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.models.dto.ProfilePostingDTO;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/user_profile")
@AllArgsConstructor
public class UserProfileRestController {

    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final PostingService postingService;

    @GetMapping("/postings")
    public ResponseEntity<List<ProfilePostingDTO>> getAllPostingsForCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Posting> postingList = postingService.getUserPostings(user);
        List<ProfilePostingDTO> dtoList = new ArrayList<>();

        ProfilePostingDTO dto;
        for (Posting posting : postingList) {
            dto = new ProfilePostingDTO();
            dto.setTitle(posting.getTitle());
            dto.setPrice(posting.getPrice());
            dto.setFavoritesCount(0);
            dto.setViewCount(0);
            dto.setUrl("#URL_WILL_BE_HERE");
            dtoList.add(dto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
