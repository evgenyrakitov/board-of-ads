package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.models.dto.ProfilePostingDTO;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;
import com.board_of_ads.service.interfaces.PostingService;
import com.board_of_ads.service.interfaces.PostingStatusService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final PostingStatusService postingStatusService;

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
            dto.setImages(posting.getImagePath());
            dtoList.add(dto);
        }

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/postings/{status}")
    public ResponseEntity<List<ProfilePostingDTO>> getAllPostingsForCurrentUserAndStatus(@PathVariable String status) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostingStatus postingStatus = postingStatusService.findPostingStatusByName(status);
        List<Posting> postingList = postingService.getUserPostingsByStatus(user, postingStatus);
        List<ProfilePostingDTO> dtoList = new ArrayList<>();


        List<PostingStatusStatistics> pss = postingStatusService.findPostingStatusStaticsByUser(user);
        System.out.println(pss);

        ProfilePostingDTO dto;
        for (Posting posting : postingList) {
            dto = new ProfilePostingDTO();
            dto.setTitle(posting.getTitle());
            dto.setPrice(posting.getPrice());
            dto.setFavoritesCount(0);
            dto.setViewCount(0);
            dto.setUrl("#URL_WILL_BE_HERE");
            dto.setImages(posting.getImagePath());
            dtoList.add(dto);
        }

        return ResponseEntity.ok(dtoList);
    }
}
