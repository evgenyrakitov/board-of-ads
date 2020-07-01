package com.avito.repository;

import com.avito.models.User;
import com.avito.models.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByUser(User user);

    List<Posting> findAllByLocationCode(String locationCode);
}
