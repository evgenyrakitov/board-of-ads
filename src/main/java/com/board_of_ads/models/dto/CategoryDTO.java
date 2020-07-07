package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private String parentCategory;
    private long id;
    private long parentId;
}
