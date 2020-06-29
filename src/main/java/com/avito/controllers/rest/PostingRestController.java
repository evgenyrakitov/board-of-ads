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
import com.avito.models.posting.Posting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
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
        return new ResponseEntity<>(new Gson().toJson(postingService.getAllPostings()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getAllPostingsForUserId(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        return new ResponseEntity<>(new Gson().toJson(postingService.getUserPostings(user)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPostingById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new Gson().toJson(postingService.getPostingById(id)), HttpStatus.OK);
    }

}
