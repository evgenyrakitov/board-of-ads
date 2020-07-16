package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationItemDTO {
        private String shortName;
        private String fullName;
        private long cityId;
        private long regionId;
}


