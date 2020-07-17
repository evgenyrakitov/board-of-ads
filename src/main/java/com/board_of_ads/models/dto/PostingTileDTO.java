package com.board_of_ads.models.dto;

import com.board_of_ads.models.Images;
import lombok.Data;

import java.util.Set;

@Data
public class PostingTileDTO {
    private String title = "";
    private String url = "";
    private long price = 0;
    private Set<Images> images;
}
