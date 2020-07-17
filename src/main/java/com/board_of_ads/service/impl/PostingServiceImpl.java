package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.dto.PostingTileDTO;
import com.board_of_ads.models.dto.ProfilePostingDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.repository.custom.PostingRepositoryCustomImpl;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<PostingTileDTO> buildTileDTOList(Iterable<Posting> postingList) {
        List<PostingTileDTO> dtoList = new ArrayList<>();
        PostingTileDTO dto;
        for (Posting posting : postingList) {
            dto = new PostingTileDTO();
            dto.setTitle(posting.getTitle());
            dto.setPrice(posting.getPrice());
            dto.setUrl("/posting/" + posting.getId());
            dto.setImages(posting.getImagePath());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<ProfilePostingDTO> buildProfilePostingDTOList(Iterable<Posting> postingList) {
        List<ProfilePostingDTO> dtoList = new ArrayList<>();
        ProfilePostingDTO dto;
        for (Posting posting : postingList) {
            dto = new ProfilePostingDTO();
            dto.setId((posting.getId()));
            dto.setTitle(posting.getTitle());
            dto.setPrice(posting.getPrice());
            dto.setFavoritesCount(0);
            dto.setViewCount(0);
            dto.setUrl("/posting/" + posting.getId());
            dto.setImages(posting.getImagePath());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<Posting> getPostingsByRegionId(String regionId) {
        return postingRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Posting> getUserPostingsByStatus(User user, PostingStatus postingStatus) {
        return postingRepository.findAllByUserAndStatus(user, postingStatus);
    }


}
