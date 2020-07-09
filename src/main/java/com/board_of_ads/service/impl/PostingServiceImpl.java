package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Posting> findAllByCategoryAndCityId(Category category, String cityId) {
        return postingRepository.findAllByCategoryAndCityId(category, cityId);
    }

    @Override
    public List<Posting> findAllByCategoryAndRegionId(Category category, String regionId) {
        return postingRepository.findAllByCategoryAndRegionId(category, regionId);
    }

    @Override
    public List<Posting> findAllByCategory(Category category) {
        return postingRepository.findAllByCategory(category);
    }


    @Override
    public List<Posting> getPostingsByCityId(String cityId) {
        return postingRepository.findAllByCityId(cityId);

    }


}
