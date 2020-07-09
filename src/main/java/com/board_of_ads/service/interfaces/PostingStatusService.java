package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;

import java.util.List;

public interface PostingStatusService {

    List<PostingStatus> getAllPostingStatuses();

    PostingStatus findPostingStatusByName(String name);

    PostingStatus findPostingStatusById(Long id);

    PostingStatus addPostingStatus(PostingStatus postingStatus);

    List<PostingStatusStatistics> findPostingStatusStaticsByUser(User user);
}
