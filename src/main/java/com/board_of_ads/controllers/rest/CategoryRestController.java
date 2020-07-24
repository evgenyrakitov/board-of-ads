package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDTO;
import com.board_of_ads.service.interfaces.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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

@RestController
@AllArgsConstructor
@RequestMapping("/rest/categories")
public class CategoryRestController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    private final CategoryService categoryService;

    @PostMapping("/{parentId}")
    public ResponseEntity<Category> create(
            @PathVariable("parentId") String parentId, Category category) {
        try {
            Long parsedParentId = Long.parseLong(parentId);
            category.setParentCategory(categoryService.findCategoryById(parsedParentId));
        } catch (NumberFormatException ignored) {
        }
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, Category category) {
        Category changedCategory = categoryService.findCategoryById(id);
        changedCategory.setNameEn(category.getNameEn());
        changedCategory.setNameRu(category.getNameRu());
        return ResponseEntity.ok(categoryService.updateCategory(changedCategory));
    }

    // We are not deleting categories, we are making them inactive
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Category disabledCategory = categoryService.findCategoryById(id);
        disabledCategory.setActive(false);
        categoryService.updateCategory(disabledCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getListOfCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @GetMapping("/dto")
    public ResponseEntity<List<CategoryDTO>> getListOfCategory(Locale locale) {
        List<Category> allCategories = categoryService.getAllCategories();
        allCategories =
                allCategories.stream()
                        .filter(Category::isActive)
                        .collect(Collectors.toList()); // Filtering only active Categories
        List<CategoryDTO> allCategoriesDTO = new ArrayList<>();
        CategoryDTO dto;
        for (Category category : allCategories) {
            dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setNameEn(category.getNameEn()); // Needed for Admin Page Categories Tab
            dto.setNameRu(category.getNameRu()); // Needed for Admin Page Categories Tab
            dto.setPostingsAmount(
                    category.getPostingsInCategory().size()); // Needed for Admin Page Categories Tab

            if ("en".equals(locale.toString())) {
                dto.setName(category.getNameEn());
                if (null != category.getParentCategory()) {
                    dto.setParentCategory(category.getParentCategory().getNameEn());
                    dto.setParentId(category.getParentCategory().getId());
                } else {
                    dto.setParentCategory(null);
                }
            } else {
                dto.setName(category.getNameRu());
                if (null != category.getParentCategory()) {
                    dto.setParentCategory(category.getParentCategory().getNameRu());
                    dto.setParentId(category.getParentCategory().getId());
                } else {
                    dto.setParentCategory(null);
                }
            }
            allCategoriesDTO.add(dto);
        }
        return ResponseEntity.ok(allCategoriesDTO);
    }
}
