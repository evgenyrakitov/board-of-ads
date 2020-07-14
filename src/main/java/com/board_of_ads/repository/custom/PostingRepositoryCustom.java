package com.board_of_ads.repository.custom;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;

import java.util.List;

public interface PostingRepositoryCustom {
    List<Posting> customSearchPostings(Category category, String searchString, Region region, City city, boolean onlyTitle, boolean onlyWithImages);
}
