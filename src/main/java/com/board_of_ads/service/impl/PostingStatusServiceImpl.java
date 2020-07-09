package com.board_of_ads.service.impl;

import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;
import com.board_of_ads.repository.PostingStatusRepository;
import com.board_of_ads.service.interfaces.PostingStatusService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostingStatusServiceImpl implements PostingStatusService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final PostingStatusRepository postingStatusRepository;

    @Override
    public List<PostingStatus> getAllPostingStatuses() {
        return postingStatusRepository.findAll();
    }

    @Override
    public PostingStatus findPostingStatusByName(String name) {
        return postingStatusRepository.findByName(name);
    }

    @Override
    public PostingStatus findPostingStatusById(Long id) {
        return postingStatusRepository.findById(id).orElse(null);
    }

    @Override
    public PostingStatus addPostingStatus(PostingStatus postingStatus) {
        return postingStatusRepository.save(postingStatus);
    }

    @Override
    public List<PostingStatusStatistics> findPostingStatusStaticsByUser(User user) {
        return postingStatusRepository.findPostingStatusStaticsByUser(user);
    }
}
