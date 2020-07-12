package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Images;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final PostingRepository postingRepository;

    @Override
    public Posting addPosting(Posting posting) {
        return postingRepository.save(posting);
    }

    @Override
    @Nullable
    public Posting getPostingById(Long id) {
        return postingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Posting> getUserPostings(User user) {
        return postingRepository.findAllByUser(user);
    }

    @Override
    public List<Posting> getAllPostings() {
        return postingRepository.findAll();
    }

    @Override
    public List<Posting> getPostingsByRegionId(String regionId) {
        return postingRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Posting> getUserPostingsByStatus(User user, PostingStatus postingStatus) {
        return postingRepository.findAllByUserAndStatus(user, postingStatus);
    }

    @Override
    public Set<Posting> findAllByFullDescriptionLike(@NonNull String fullDescription) {
        return postingRepository.findAllByFullDescriptionLikeIgnoreCase(fullDescription);
    }

    @Override
    public Set<Posting> findAllByTitleLike(@NonNull String title) {
        return postingRepository.findAllByTitleLikeIgnoreCaseAndImagePathIsNull(title);
    }

    @Override
    public Set<Posting> findAllByFullDescriptionLikeAndImagePathIsNotNull(@NonNull String fullDescription) {
        return postingRepository.findAllByFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(fullDescription);
    }

    @Override
    public Set<Posting> findAllByTitleLikeAndImagePathIsNotNull(@NonNull String title) {
        return postingRepository.findAllByTitleLikeIgnoreCaseAndImagePathIsNotNull(title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndFullDescriptionLike(String cityId, @NonNull String fullDescription) {
        return postingRepository.findAllByCityIdAndFullDescriptionLikeIgnoreCase(cityId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCityIdAndFullDescriptionLikeAndImagePathIsNotNull(String cityId, @NonNull String fullDescription) {
        return postingRepository.findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(
                cityId, fullDescription
        );
    }

    @Override
    public Set<Posting> findAllByCityIdAndTitleLike(String cityId, @NonNull String title) {
        return postingRepository.findAllByCityIdAndTitleLikeIgnoreCase(cityId, title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndTitleLikeAndImagePathIsNotNull(String cityId, @NonNull String title) {
        return postingRepository.findAllByCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(cityId, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndFullDescriptionLike(String regionId, @NonNull String fullDescription) {
        return postingRepository.findAllByRegionIdAndFullDescriptionLikeIgnoreCase(regionId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(String regionId, @NonNull String fullDescription) {
        return postingRepository.findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(
                regionId, fullDescription
        );
    }

    @Override
    public Set<Posting> findAllByRegionIdAndTitleLike(String regionId, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndTitleLikeIgnoreCase(regionId, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndTitleLikeAndImagePathIsNotNull(String regionId, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
                regionId, title
        );
    }

    @Override
    public Set<Posting> findAllByCategory(@NonNull Category category) {
        return postingRepository.findAllByCategory(category);
    }

    @Override
    public Set<Posting> findAllByCategoryAndFullDescriptionLike(@NonNull Category category, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndFullDescriptionLikeIgnoreCase(category, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndFullDescriptionLikeAndImagePathIsNotNull(@NonNull Category category, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(category, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndTitleLike(@NonNull Category category, @NonNull String title) {
        return postingRepository.findAllByCategoryAndTitleLikeIgnoreCase(category, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndTitleLikeAndImagePathIsNotNull(@NonNull Category category, @NonNull String title) {
        return postingRepository.findAllByCategoryAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionId(@NonNull Category category, String regionId) {
        return postingRepository.findAllByCategoryAndRegionId(category, regionId);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndFullDescriptionLike(@NonNull Category category, String regionId, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCase(category, regionId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndFullDescriptionLikeAndImagePathIsNotNull(@NonNull Category category, String regionId, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(category, regionId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndTitleLike(@NonNull Category category, String regionId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndTitleLikeIgnoreCase(category, regionId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndTitleLikeAndImagePathIsNotNull(@NonNull Category category, String regionId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, regionId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityId(@NonNull Category category, String cityId) {
        return postingRepository.findAllByCategoryAndCityId(category, cityId);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndFullDescriptionLike(@NonNull Category category, String cityId, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCase(
                category, cityId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndFullDescriptionLikeAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String fullDescription) {
        return postingRepository.findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndImagePathIsNotNull(
               category, cityId, fullDescription);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndTitleLike(@NonNull Category category, String cityId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndTitleLikeIgnoreCase(category, cityId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndTitleLikeAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(
                category, cityId, title);
    }

    @Override
    public List<Posting> getPostingsByCityId(String cityId) {
        return postingRepository.findAllByCityId(cityId);
    }



}
