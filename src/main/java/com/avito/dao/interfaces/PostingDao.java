package com.avito.dao.interfaces;

import com.avito.models.User;
import com.avito.models.posting.Posting;

import java.util.List;

public interface PostingDao {

    Posting addPosting(Posting posting);

    Posting getPostingById(Long id);

    List<Posting> getUserPostings(User user);

    List<Posting> getAllPostings();

}


