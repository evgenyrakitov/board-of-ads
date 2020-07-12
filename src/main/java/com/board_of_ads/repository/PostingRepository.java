package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface PostingRepository extends JpaRepository<Posting, Long>{

    List<Posting> findAllByUser(User user);

    List<Posting> findAllByUserAndStatus(User user, PostingStatus postingStatus);

    List<Posting> findAllByCityId(String cityId);

    List<Posting> findAllByRegionId(String regionId);




    Set<Posting> findAllByFullDescriptionLikeIgnoreCase(@NonNull String fullDescription);

    Set<Posting>findAllByTitleLikeIgnoreCaseAndImagePathIsNull(@NonNull String title);

    Set<Posting>findAllByFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String fullDescription);

    Set<Posting>findAllByTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String title);


    Set<Posting> findAllByCityIdAndFullDescriptionLikeIgnoreCase(String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCityIdAndTitleLikeIgnoreCase(String cityId, @NonNull String title);

    Set<Posting>findAllByCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String title);


    Set<Posting>findAllByRegionIdAndFullDescriptionLikeIgnoreCase(String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByRegionIdAndTitleLikeIgnoreCase(String regionId, @NonNull String title);

    Set<Posting>findAllByRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String title);


    Set<Posting>findAllByCategory(@NonNull Category category);

    Set<Posting>findAllByCategoryAndFullDescriptionLikeIgnoreCase(@NonNull Category category, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndTitleLikeIgnoreCase(@NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, @NonNull String title);

    Set<Posting>findAllByCategoryAndRegionId(@NonNull Category category, String regionId);

    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCase(
            @NonNull Category category, String regionId, @NonNull String fullDescription);


    Set<Posting>findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndRegionIdAndTitleLikeIgnoreCase(
            @NonNull Category category, String regionId, @NonNull String title);


    Set<Posting>findAllByCategoryAndRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
            @NonNull Category category, String regionId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityId(@NonNull Category category, String cityId);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String fullDescription);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String title);

    Set<Posting>findAllByCategoryAndCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String title);


}
