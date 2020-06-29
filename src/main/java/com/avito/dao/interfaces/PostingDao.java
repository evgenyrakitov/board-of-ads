package com.avito.dao.interfaces;

import com.avito.models.posting.Posting;


public interface PostingDao {
    Posting findPostingById (Long id);
}
