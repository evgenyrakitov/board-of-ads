package com.board_of_ads.models.dto;

import com.board_of_ads.models.Images;
import com.board_of_ads.models.kladr.City;
import lombok.Data;

import java.util.Set;

@Data
public class ProfilePostingDTO {
    private Long id;
    private String dataPostinga;
    private String cityId;
    private String title = "";
    private String url = "";
    private String price;
    private int viewCount = 0;
    private int favoritesCount = 0;
    private Set<Images> images;
}
