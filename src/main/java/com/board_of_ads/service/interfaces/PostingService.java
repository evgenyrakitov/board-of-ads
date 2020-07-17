package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.dto.PostingTileDTO;
import com.board_of_ads.models.dto.ProfilePostingDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;

import java.util.List;

public interface PostingService {

    Posting addPosting(Posting posting);

    Posting getPostingById(Long id);

    List<Posting> getUserPostings(User user);

    List<Posting> getAllPostings();

    List<Posting> getPostingsByCityId(String cityId);

    List<Posting> getPostingsByRegionId(String regionId);

    List<Posting> getUserPostingsByStatus(User principal, PostingStatus postingStatus);

    List<Posting> customSearchPostings(Category category, String searchString, Region region, City city, boolean onlyTitle, boolean onlyWithImages);

    List<PostingTileDTO> buildTileDTOList(Iterable<Posting> postingList);

    List<ProfilePostingDTO> buildProfilePostingDTOList(Iterable<Posting> postingList);
}
