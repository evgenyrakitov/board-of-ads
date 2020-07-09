package com.board_of_ads.repository;

import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingStatusRepository extends JpaRepository<PostingStatus, Long> {

    PostingStatus findByName(String name);

    @Query("SELECT " +
            "    new com.board_of_ads.models.posting.extra.PostingStatusStatistics(p.status, COUNT(p)) " +
            "FROM " +
            "    Posting p " +
            "WHERE " +
            "    p.user = :user " +
            "GROUP BY " +
            "    p.status")
    List<PostingStatusStatistics> findPostingStatusStaticsByUser(User user);
}
