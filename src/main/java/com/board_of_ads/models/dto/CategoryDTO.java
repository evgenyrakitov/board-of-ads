package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private String nameRu;   // Needed for Admin Page Categories Tab
    private String nameEn;   // Needed for Admin Page Categories Tab
    private String parentCategory;
    private long id;
    private long parentId;
    private long postingsAmount;   // Needed for Admin Page Categories Tab
}
