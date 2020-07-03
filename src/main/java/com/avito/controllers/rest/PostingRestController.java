package com.avito.controllers.rest;

import com.avito.models.Category;
import com.avito.models.Images;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.PostingService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/posting")
@AllArgsConstructor
public class PostingRestController {

    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final PostingService postingService;

    @GetMapping("/getPostingInfo")
    public ResponseEntity<Posting> getPosting() {
        Set<Images> img = new HashSet<>();
        Posting posting = new Posting(
                "Коттедж 400 м² на участке 3 сот.",
                new Category(),
                new User(),
                "Коттедж на два хозяина. На первом этаже кухня, зал и туалет с душем, второй этаж три комнаты и туалет с ванной, третий этаж-две комнаты. Цокольный этаж с гаражом и комнатой. Готов к заселению, возможна долгосрочная аренда.",
                "Коттедж"
        );
        posting.setImagePath(img);
        posting.setPrice(123123L);
        return ResponseEntity.ok(posting);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllPostings() {
        return ResponseEntity.ok(new Gson().toJson(postingService.getAllPostings()));
    }

    @GetMapping("/all/{locationCode}")
    public ResponseEntity<List<Posting>> getPostingsByLocationCode(@PathVariable String locationCode) {
        return ResponseEntity.ok(postingService.getPostingsByLocationCode(locationCode));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getAllPostingsForUserId(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(new Gson().toJson(postingService.getUserPostings(user)));
    }

    @GetMapping("/user/current")
    public ResponseEntity<String> getAllPostingsForCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new Gson().toJson(postingService.getUserPostings(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPostingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new Gson().toJson(postingService.getPostingById(id)));
    }

    @GetMapping("/getProductCards")
    public ResponseEntity<List<Posting>> getProductCards() {
        Set<Images> img = new HashSet<>();
        Images images = new Images();
        images.setImagePath("/images/image-placeholder.png");
        img.add(images);
        Posting posting = new Posting();
        posting.setPrice(123123L);
        posting.setImagePath(img);
        posting.setShortDescription("Краткое описание");
        posting.setTitle("Автокресло 0-1");
        List<Posting> list = new ArrayList<>();
        for (int i = 0; i< 77; i++){
            list.add(posting);
        }
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
