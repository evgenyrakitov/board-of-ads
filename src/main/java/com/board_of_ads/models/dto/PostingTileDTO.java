package com.board_of_ads.models.dto;

import com.board_of_ads.models.Images;
import lombok.Data;

import java.util.Set;

/**
 * ДТО для плиток с объявлениями на сайте.
 */

@Data
public class PostingTileDTO {
    private Long id;
    private String title = "";
    private String url = "";
    private long price = 0;
    private Set<Images> images;
}
