package com.board_of_ads.repository.custom;

import com.board_of_ads.models.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepositoryCustom extends JpaRepository<Posting, Long> {

}
