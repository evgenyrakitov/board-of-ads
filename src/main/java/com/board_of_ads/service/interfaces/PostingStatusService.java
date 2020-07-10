package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;

import java.util.List;
import java.util.Locale;

public interface PostingStatusService {

    List<PostingStatus> getAllPostingStatuses();

    PostingStatus getPostingStatusByName(String name);

    PostingStatus getPostingStatusById(Long id);

    PostingStatus addPostingStatus(PostingStatus postingStatus);

    List<PostingStatusStatistics> findPostingStatusStaticsByUser(User user);

    PostingStatus localizeDescription(PostingStatus postingStatus, Locale locale);

    List<PostingStatusStatistics> localizeDescription(List<PostingStatusStatistics> postingStatusStatisticsList, Locale locale);
}
