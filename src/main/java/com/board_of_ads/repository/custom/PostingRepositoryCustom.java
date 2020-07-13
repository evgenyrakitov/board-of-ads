package com.board_of_ads.repository.custom;

import com.board_of_ads.models.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostingRepositoryCustom extends CrudRepository<Posting, Long> {

}
