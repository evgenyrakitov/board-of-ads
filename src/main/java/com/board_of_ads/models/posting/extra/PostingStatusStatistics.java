package com.board_of_ads.models.posting.extra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostingStatusStatistics {

    PostingStatus status;
    Long count;
}
