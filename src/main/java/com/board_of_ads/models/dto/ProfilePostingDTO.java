package com.board_of_ads.models.dto;

import com.board_of_ads.models.Images;
import lombok.Data;

import java.util.Set;

/**
 * ДТО для передачи блоков с объявлениями для страницы /profile и вкладки "Мои объявления"
 * Для плиток с объявлениями на главной странице лучше использовать PostingTileDTO
 */

@Data
public class ProfilePostingDTO {
    private Long id;
    private String title = "";
    private String url = "";
    private long price = 0;
    private int viewCount = 0;
    private int favoritesCount = 0;
    private Set<Images> images;
}
