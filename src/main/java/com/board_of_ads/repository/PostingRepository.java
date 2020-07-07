package com.board_of_ads.repository;

import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByUser(User user);

    List<Posting> findAllByCityId(String cityId);

    List<Posting> findAllByRegionId(String regionId);
}
