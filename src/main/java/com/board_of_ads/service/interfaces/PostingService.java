package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Images;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import lombok.NonNull;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface PostingService {

    Posting addPosting(Posting posting);

    Posting getPostingById(Long id);

    List<Posting> getUserPostings(User user);

    List<Posting> getAllPostings();

    List<Posting> getPostingsByCityId(String cityId);

    List<Posting> getPostingsByRegionId(String regionId);

    List<Posting> getUserPostingsByStatus(User principal, PostingStatus postingStatus);

    Set<Posting>findAllByFullDescriptionLike(@NonNull String fullDescription);

    Set<Posting>findAllByTitleLike(@NonNull String title);

    Set<Posting>findAllByFullDescriptionLikeAndImagePathIsNotNull(@NonNull String fullDescription);

    Set<Posting>findAllByTitleLikeAndImagePathIsNotNull(@NonNull String title);

    Set<Posting> findAllByCityIdAndFullDescriptionLike(String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCityIdAndFullDescriptionLikeAndImagePathIsNotNull(String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCityIdAndTitleLike(String cityId, @NonNull String title);

    Set<Posting>findAllByCityIdAndTitleLikeAndImagePathIsNotNull(String cityId, @NonNull String title);

    Set<Posting>findAllByRegionIdAndFullDescriptionLike(String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByRegionIdAndTitleLike(String regionId, @NonNull String title);

    Set<Posting>findAllByRegionIdAndTitleLikeAndImagePathIsNotNull(String regionId, @NonNull String title);

    Set<Posting>findAllByCategory(@NonNull Category category);

    Set<Posting>findAllByCategoryAndFullDescriptionLike(@NonNull Category category, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndFullDescriptionLikeAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndTitleLike(@NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndTitleLikeAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndRegionId(@NonNull Category category, String regionId);

    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLike(
            @NonNull Category category, String regionId, @NonNull String fullDescription);


    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndRegionIdAndTitleLike(
            @NonNull Category category, String regionId, @NonNull String title);


    Set<Posting>findAllByCategoryAndRegionIdAndTitleLikeAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityId(@NonNull Category category, String cityId);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLike(@NonNull Category category, String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLikeAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLike(@NonNull Category category, String cityId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLikeAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String title);



}
