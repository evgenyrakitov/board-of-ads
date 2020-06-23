package com.avito.controllers.rest;

import com.avito.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class HeaderRestController {

    @GetMapping("/getListCategory")
    public ResponseEntity<Category> getListOfCategory() {
        Category c1 = new Category("AVTO", null);
        Category c2 = new Category("AVTO1", null);
        Category c3 = new Category("AVTO2", null);
        Category c4 = new Category("AVTOvvdsc", null);
        Category c5 = new Category("AVTOdvdf23c", null);
        Category c6 = new Category("AVTdc32f", null);
        Category c7 = new Category("AVTOdcasd", null);
        Category c8 = new Category("AVTOadf", null);
        Set<Category> list2 = new HashSet<>();
        list2.add(c1);
        list2.add(c2);
        list2.add(c3);
        list2.add(c4);
        list2.add(c5);
        list2.add(c6);
        list2.add(c7);
        list2.add(c8);
        Category category1 = new Category("AVTO", list2);
        Category category2 = new Category("Mebel", list2);
        Category category3 = new Category("Dom", list2);
        Category category4 = new Category("Java", list2);
        Set<Category> list = new HashSet<>();
        list.add(category1);
        list.add(category2);
        list.add(category3);
        list.add(category4);
        Category category0 = new Category("ALL", list);
        return category0 != null
                ? new ResponseEntity<Category>(category0, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/avitoNavigation")
    public ResponseEntity<List<String>> getAvitoNavigation() {
        List<String> list = new ArrayList<>();
        list.add("Объявления");
        list.add("Магазины");
        list.add("Бизнес");
        list.add("Бизнес");
        list.add("Помощь");
        return list != null && !list.isEmpty()
                ? new ResponseEntity<List<String>>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
