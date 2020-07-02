package com.avito.service.interfaces;

import com.avito.models.User;
import com.avito.models.posting.Posting;

import java.util.List;

public interface PostingService {

    Posting addPosting(Posting posting);

    Posting getPostingById(Long id);

    List<Posting> getUserPostings(User user);

    List<Posting> getAllPostings();

    List<Posting> getPostingsByLocationCode(String locationCode);

}
