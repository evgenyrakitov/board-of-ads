package com.board_of_ads.models.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String title;
    private String content;
    private boolean isRead;
}
