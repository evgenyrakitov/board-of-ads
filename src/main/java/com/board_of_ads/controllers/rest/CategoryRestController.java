package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDTO;
import com.board_of_ads.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/categories")
public class CategoryRestController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getListOfCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/dto")
    public ResponseEntity<List<CategoryDTO>> getListOfCategory(Locale locale) {
        List<Category> allCategories = categoryService.getAllCategories();
        List<CategoryDTO> allCategoriesDTO = new ArrayList<>();
        CategoryDTO dto;
        for (Category allCategory : allCategories) {
            dto = new CategoryDTO();
            dto.setId(allCategory.getId());
            if ("en".equals(locale.toString())) {
                dto.setName(allCategory.getNameEn());

                if (null != allCategory.getParentCategory()) {
                    dto.setParentCategory(allCategory.getParentCategory().getNameEn());
                    dto.setParentId(allCategory.getParentCategory().getId());
                } else {
                    dto.setParentCategory(null);
                }
            } else {
                dto.setName(allCategory.getNameRu());
                if (null != allCategory.getParentCategory()) {
                    dto.setParentCategory(allCategory.getParentCategory().getNameRu());
                    dto.setParentId(allCategory.getParentCategory().getId());
                } else {
                    dto.setParentCategory(null);
                }
            }

            allCategoriesDTO.add(dto);
        }
        return ResponseEntity.ok(allCategoriesDTO);
    }
    @GetMapping("/root_categories")
    public ResponseEntity<List<Category>> getRootCategories() {
        return ResponseEntity.ok(categoryService.getRootCategories());
    }
    @GetMapping("/getCategoriesByParentCategory/{id}")
    public ResponseEntity<List<Category>> getCategoriesByParentCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        List<Category> categoriesByParentCategory = categoryService.getCategoriesByParentCategory(category);
        return ResponseEntity.ok(categoriesByParentCategory);
    }
}
