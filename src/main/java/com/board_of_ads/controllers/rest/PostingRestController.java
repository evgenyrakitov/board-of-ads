package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Images;
import com.board_of_ads.models.User;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.PostingService;
import com.board_of_ads.service.interfaces.RegionService;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CityService cityService;

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

    @GetMapping("/searchByCity/{cityId}")
    public ResponseEntity<List<Posting>> getPostingsByCityId(@PathVariable("cityId") String cityId) {
        return ResponseEntity.ok(postingService.getPostingsByCityId(cityId));
    }

    @GetMapping("/searchByRegion/{regionId}")
    public ResponseEntity<List<Posting>> getPostingsByRegionId(@PathVariable("regionId") String regionId) {
        return ResponseEntity.ok(postingService.getPostingsByRegionId(regionId));
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
        for (int i = 0; i < 77; i++) {
            list.add(posting);
        }
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<Posting>> getSearchForm(@RequestParam String category,
                                                      @RequestParam String search,
                                                      @RequestParam String regionCity,
                                                      @RequestParam(required = false) String ch1,
                                                      @RequestParam(required = false) String ch2) {
        Category categ = null;
        Region region = null;
        City city = null;
        Set<Posting> postings = null;
        String search_ = "%"+search+"%";



        if (category.length() != 0) {
            categ = categoryService.findCategoryByNameRu(category);
        }

        if (regionCity.length() != 0) {
            for (String reg : regionCity.split(" ")) {
                city = cityService.findCityByName(reg);
                if (city != null) break;
                region = regionService.findRegionByName(reg);
            }
        }
        if (categ == null) {
            if (city == null) {
                if (region == null) {
                    if (search.length() == 0) {
                        postings = new HashSet<>(postingService.getAllPostings());
                    } else if (ch1 == null && ch2 == null) {
                        postings = postingService.findAllByFullDescriptionLike(search_);
                    } else if (ch1 == null && ch2 != null) {
                        postings = postingService.findAllByFullDescriptionLikeAndImagePathIsNotNull(search_);
                    } else if (ch1 != null && ch2 == null) {
                        postings = postingService.findAllByTitleLike(search_);
                    } else {
                        postings = postingService.findAllByTitleLikeAndImagePathIsNotNull(search_);
                    }
                } else if (search.length() == 0) {
                    postings = new HashSet<>(postingService.getPostingsByRegionId(region.getId().toString()));
                } else if (ch1 == null && ch2 == null) {
                    postings = postingService.findAllByRegionIdAndFullDescriptionLike(
                            region.getId().toString(), search_);
                } else if (ch1 == null && ch2 != null) {
                    postings = postingService.findAllByRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(
                            region.getId().toString(), search_
                    );
                } else if (ch1 != null && ch2 == null) {
                    postings = postingService.findAllByRegionIdAndTitleLike(region.getId().toString(), search_);
                } else {
                    postings = postingService.findAllByRegionIdAndTitleLikeAndImagePathIsNotNull(region.getId().toString(), search_);
                }
            } else if (search.length() == 0){
                postings = new HashSet<>(postingService.getPostingsByCityId(city.getId().toString()));
            } else if (ch1 == null && ch2 == null){
                postings = postingService.findAllByCityIdAndFullDescriptionLike(city.getId().toString(), search_);
            } else if (ch1 == null && ch2 != null){
                postings = postingService.findAllByCityIdAndFullDescriptionLikeAndImagePathIsNotNull(
                        city.getId().toString(), search_
                );
            } else if (ch1 != null && ch2 == null){
                postings = postingService.findAllByCityIdAndTitleLike(city.getId().toString(), search_);
            } else {
                postings = postingService.findAllByCityIdAndTitleLikeAndImagePathIsNotNull(
                        city.getId().toString(), search_
                );
            }
        } else if (city == null){
            if(region == null){
                if (search.length() == 0){
                    postings = postingService.findAllByCategory(categ);
                } else if (ch1 == null && ch2 == null){
                    postings = postingService.findAllByCategoryAndFullDescriptionLike(categ, search_);
                } else if (ch1 == null && ch2 != null){
                    postings = postingService.findAllByCategoryAndFullDescriptionLikeAndImagePathIsNotNull(
                            categ, search_
                    );
                }else if (ch1 != null && ch2 == null){
                    postings = postingService.findAllByCategoryAndTitleLike(categ, search_);
                } else {
                    postings = postingService.findAllByCategoryAndTitleLikeAndImagePathIsNotNull(categ, search_);
                }
            } else if (search.length() == 0){
                postings = postingService.findAllByCategoryAndRegionId(categ, region.getId().toString());
            } else if (ch1 == null && ch2 == null){
                postings = postingService.findAllByCategoryAndRegionIdAndFullDescriptionLike(categ, region.getId().toString(), search_);
            } else if (ch1 == null && ch2 != null){
                postings = postingService.findAllByCategoryAndRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(
                        categ, region.getId().toString(), search_
                );
            } else if (ch1 != null && ch2 == null){
                postings = postingService.findAllByCategoryAndRegionIdAndTitleLike(categ, region.getId().toString(), search_);
            } else {
                postings = postingService.findAllByCategoryAndRegionIdAndTitleLikeAndImagePathIsNotNull(
                        categ, region.getId().toString(), search_
                );
            }
        } else {
            if (search.length() == 0){
                postings = postingService.findAllByCategoryAndCityId(categ, city.getId().toString());
            } else if (ch1 == null && ch2 == null){
                postings = postingService.findAllByCategoryAndCityIdAndFullDescriptionLike(categ, city.getId().toString(), search_);
            } else if (ch1 == null && ch2 != null){
                postings = postingService.findAllByCategoryAndCityIdAndFullDescriptionLikeAndImagePathIsNotNull(
                        categ, city.getId().toString(), search_
                );
            } else if (ch1 != null && ch2 == null){
                postings = postingService.findAllByCategoryAndCityIdAndTitleLike(categ, city.getId().toString(), search_);
            } else {
                postings = postingService.findAllByCategoryAndCityIdAndTitleLikeAndImagePathIsNotNull(
                        categ, city.getId().toString(), search_
                );
            }
        }

            return ResponseEntity.ok(postings);
    }
}

