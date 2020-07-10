package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Images;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public interface PostingService {

    Posting addPosting(Posting posting);

    Posting getPostingById(Long id);

    List<Posting> getUserPostings(User user);

   List<Posting> getAllPostings();

    List<Posting> getPostingsByCityId(String cityId);

    List<Posting> getPostingsByRegionId(String regionId);

    List<Posting> findAllByCategoryAndCityIdAndRegionIdAndTitleLikeOrFullDescriptionLikeAndImagePath
            (@NonNull Category category,
             @NonNull String cityId,
             @NonNull String regionId,
             @NonNull String title,
             @NonNull String fullDescription,
             @NonNull Set<Images> imagePath);






}
