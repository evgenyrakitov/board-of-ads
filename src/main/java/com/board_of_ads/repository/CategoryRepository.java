package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.parentCategory is null")
    List<Category> getRootCategories();

    Category findCategoryByNameRu(String nameRu);



}
