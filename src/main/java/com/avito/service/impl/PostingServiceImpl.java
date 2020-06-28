package com.avito.service.impl;

import com.avito.dao.interfaces.PostingDao;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final PostingDao postingDao;

    @Override
    public Posting addPosting(Posting posting) {
        return postingDao.addPosting(posting);
    }

    @Override
    public Posting getPostingById(Long id) {
        return postingDao.getPostingById(id);
    }

    @Override
    public List<Posting> getUserPostings(User user) {
        return postingDao.getUserPostings(user);
    }

    @Override
    public List<Posting> getAllPostings() {
        return postingDao.getAllPostings();
    }
}
