package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для передачи локейшена на клиент.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationItemDTO {
    private String shortName;
    private String beautyName;
    private long cityId;
    private long regionId;
}


