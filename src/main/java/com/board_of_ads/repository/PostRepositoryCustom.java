package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.posting.Posting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepositoryCustom {

    @Query("select p from Posting p where p.category = :category and p.cityId = :name" +
            " and p.title like : search% and p.imagePath.size = :images")
    List<Posting> getSearchPostings(@Param("category") Category category,
                                    @Param("name") String name,
                                    @Param("search") String search,
                                    @Param("images") int images);
}

