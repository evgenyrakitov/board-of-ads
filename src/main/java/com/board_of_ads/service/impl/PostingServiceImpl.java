package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.repository.custom.PostingRepositoryCustomImpl;
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
    private final PostingRepositoryCustomImpl postingRepositoryCustomImpl;


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
    public List<Posting> getPostingsByCityId(String cityId) {
        return postingRepository.findAllByCityId(cityId);
    }

    @Override
    public List<Posting> customSearchPostings(Category category, String searchString, Region region, City city, boolean onlyTitle, boolean onlyWithImages) {
        return postingRepositoryCustomImpl.customSearchPostings(category, searchString, region, city, onlyTitle, onlyWithImages);
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
    public Set<Posting> findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByTitleLikeIgnoreCase(@NonNull String title) {
        return postingRepository.findAllByTitleLikeIgnoreCase(title);
    }

    @Override
    public Set<Posting> findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull String title) {
        return postingRepository.findAllByTitleLikeIgnoreCaseAndImagePathIsNotNull(title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(String cityId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(cityId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(cityId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndTitleLikeIgnoreCase(String cityId, @NonNull String title) {
        return postingRepository.findAllByCityIdAndTitleLikeIgnoreCase(cityId, title);
    }

    @Override
    public Set<Posting> findAllByCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String cityId, @NonNull String title) {
        return postingRepository.findAllByCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(cityId, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(String regionId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(regionId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(regionId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndTitleLikeIgnoreCase(String regionId, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndTitleLikeIgnoreCase(regionId, title);
    }

    @Override
    public Set<Posting> findAllByRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(String regionId, @NonNull String title) {
        return postingRepository.findAllByRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(regionId, title);
    }

    @Override
    public Set<Posting> findAllByCategory(@NonNull Category category) {
        return postingRepository.findAllByCategory(category);
    }

    @Override
    public Set<Posting> findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull Category category, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(category, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndTitleLikeIgnoreCase(@NonNull Category category, @NonNull String title) {
        return postingRepository.findAllByCategoryAndTitleLikeIgnoreCase(category, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, @NonNull String title) {
        return postingRepository.findAllByCategoryAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionId(@NonNull Category category, String regionId) {
        return postingRepository.findAllByCategoryAndRegionId(category, regionId);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull Category category, String regionId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(category, regionId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String regionId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, regionId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndTitleLikeIgnoreCase(@NonNull Category category, String regionId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndTitleLikeIgnoreCase(category, regionId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String regionId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndRegionIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, regionId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityId(@NonNull Category category, String cityId) {
        return postingRepository.findAllByCategoryAndCityId(category, cityId);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCase(category, cityId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String fullDescription, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndFullDescriptionLikeIgnoreCaseAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, cityId, fullDescription, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndTitleLikeIgnoreCase(@NonNull Category category, String cityId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndTitleLikeIgnoreCase(category, cityId, title);
    }

    @Override
    public Set<Posting> findAllByCategoryAndCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(@NonNull Category category, String cityId, @NonNull String title) {
        return postingRepository.findAllByCategoryAndCityIdAndTitleLikeIgnoreCaseAndImagePathIsNotNull(category, cityId, title);
    }


}
