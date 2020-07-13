package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.models.posting.extra.PostingStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByUser(User user);

    List<Posting> findAllByUserAndStatus(User user, PostingStatus postingStatus);

    List<Posting> findAllByCityId(String cityId);

    List<Posting> findAllByRegionId(String regionId);
}
