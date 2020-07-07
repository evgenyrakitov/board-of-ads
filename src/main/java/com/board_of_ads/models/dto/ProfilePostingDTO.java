package com.board_of_ads.models.dto;

import lombok.Data;

@Data
public class ProfilePostingDTO {
    private String title = "";
    private String url = "";
    private long price = 0;
    private int viewCount = 0;
    private int favoritesCount = 0;
}
