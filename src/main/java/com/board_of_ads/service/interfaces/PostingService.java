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

    Set<Posting> findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull String fullDescription, @NonNull String title);

    Set<Posting> findAllByTitleLikeIgnoreCase(@NonNull String title);

    Set<Posting>findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String title);


    Set<Posting> findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(String cityId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCityIdAndTitleLikeIgnoreCase(String cityId, @NonNull String title);

    Set<Posting>findAllByCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String title);


    Set<Posting>findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(String regionId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByRegionIdAndTitleLikeIgnoreCase(String regionId, @NonNull String title);

    Set<Posting>findAllByRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String title);


    Set<Posting>findAllByCategory(@NonNull Category category);

    Set<Posting>findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull Category category, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCategoryAndTitleLikeIgnoreCase(@NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndRegionId(@NonNull Category category, String regionId);

    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(
            @NonNull Category category, String regionId, @NonNull String fullDescription, @NonNull String title);


    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCategoryAndRegionIdAndTitleLikeIgnoreCase(
            @NonNull Category category, String regionId, @NonNull String title);


    Set<Posting>findAllByCategoryAndRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityId(@NonNull Category category, String cityId);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String fullDescription, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String title);




}
