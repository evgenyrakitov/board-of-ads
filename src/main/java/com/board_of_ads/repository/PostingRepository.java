package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Images;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByUser(User user);

    List<Posting> findAllByCityId(String cityId);

    List<Posting> findAllByRegionId(String regionId);

    List<Posting>findAllByCategory(Category category);

    List<Posting>findAllByCategoryOrCategoryIsNullAndCityIdA()



}
