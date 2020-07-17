package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.PostingTileDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.PostingService;
import com.board_of_ads.service.interfaces.RegionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/rest/posting")
@AllArgsConstructor
public class PostingRestController {

    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final PostingService postingService;
    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<PostingTileDTO>> getAllPostings() {
        List<Posting> postings = postingService.getAllPostings();
        return ResponseEntity.ok(postingService.buildTileDTOList(postings));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostingTileDTO>> getSearchForm(
            @RequestParam String category,
            @RequestParam String search,
            @RequestParam Long searchCityId,
            @RequestParam Long searchRegionId,
            @RequestParam(required = false) String ch1,
            @RequestParam(required = false) String ch2) {
        Category categ = null;
        Region region = null;
        City city = null;
        List<Posting> postings = new ArrayList<>();
        String search_ = "%" + search.replaceAll("\\pP", "") + "%";


        if (category.length() != 0) {
            categ = categoryService.findCategoryById(parseLong(category));
        }

        if (searchRegionId != null && searchRegionId > 0) {
            region = regionService.findById(searchRegionId);
        }

        if (searchCityId != null && searchCityId > 0) {
            city = cityService.findById(searchCityId);
        }

        boolean onlyTitle = ch1 != null;
        boolean onlyWithImages = ch2 != null;


        if (categ != null) {
            postings.addAll(postingService.customSearchPostings(categ, search_, region, city, onlyTitle, onlyWithImages));
            for (Category c1 : categoryService.findAllByParentCategory(categ)) {
                postings.addAll(postingService.customSearchPostings(c1, search_, region, city, onlyTitle, onlyWithImages));
                for (Category c2 : categoryService.findAllByParentCategory(c1)) {
                    postings.addAll(postingService.customSearchPostings(c2, search_, region, city, onlyTitle, onlyWithImages));
                }
            }
        } else {
            postings.addAll(postingService.customSearchPostings(categ, search_, region, city, onlyTitle, onlyWithImages));
        }

        return ResponseEntity.ok(postingService.buildTileDTOList(postings));
    }


//    @GetMapping("/getPostingInfo")
//    public ResponseEntity<Posting> getPosting() {
//        Set<Images> img = new HashSet<>();
//        Posting posting = new Posting(
//                "Коттедж 400 м² на участке 3 сот.",
//                new Category(),
//                new User(),
//                "Коттедж на два хозяина. На первом этаже кухня, зал и туалет с душем, второй этаж три комнаты и туалет с ванной, третий этаж-две комнаты. Цокольный этаж с гаражом и комнатой. Готов к заселению, возможна долгосрочная аренда.",
//                "Коттедж"
//        );
//        posting.setImagePath(img);
//        posting.setPrice(123123L);

//        return ResponseEntity.ok(posting);

//    }
//    @GetMapping("/searchByCity/{cityId}")
//    public ResponseEntity<List<ProfilePostingDTO>> getPostingsByCityId(@PathVariable("cityId") String cityId) {
//        List<Posting> postings = postingService.getPostingsByCityId(cityId);
//        return ResponseEntity.ok(UserProfileRestController.buildDTOList(postings));
//    }
//
//    @GetMapping("/searchByRegion/{regionId}")
//    public ResponseEntity<List<ProfilePostingDTO>> getPostingsByRegionId(@PathVariable("regionId") String regionId) {
//        List<Posting> postings = postingService.getPostingsByRegionId(regionId);
//        return ResponseEntity.ok(UserProfileRestController.buildDTOList(postings));

//    }
//    @GetMapping("/user/{id}")
//    public ResponseEntity<String> getAllPostingsForUserId(@PathVariable("id") Long id) {
//        User user = new User();
//        user.setId(id);
//        return ResponseEntity.ok(new Gson().toJson(postingService.getUserPostings(user)));

//    }
//    @GetMapping("/user/current")
//    public ResponseEntity<String> getAllPostingsForCurrentUser() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok(new Gson().toJson(postingService.getUserPostings(user)));

//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<String> getPostingById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(new Gson().toJson(postingService.getPostingById(id)));

//    }
//    @GetMapping("/getProductCards")
//    public ResponseEntity<List<Posting>> getProductCards() {
//        Set<Images> img = new HashSet<>();
//        Images images = new Images();
//        images.setImagePath("/images/image-placeholder.png");
//        img.add(images);
//        Posting posting = new Posting();
//        posting.setPrice(123123L);
//        posting.setImagePath(img);
//        posting.setShortDescription("Краткое описание");
//        posting.setTitle("Автокресло 0-1");
//        List<Posting> list = new ArrayList<>();
//        for (int i = 0; i < 77; i++) {
//            list.add(posting);
//        }
//        return !list.isEmpty()
//                ? new ResponseEntity<>(list, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

//    }


}

