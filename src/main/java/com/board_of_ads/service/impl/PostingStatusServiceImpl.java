package com.board_of_ads.service.impl;

import com.board_of_ads.extensions.CustomMessageSource;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.extra.PostingStatus;
import com.board_of_ads.models.posting.extra.PostingStatusStatistics;
import com.board_of_ads.repository.PostingStatusRepository;
import com.board_of_ads.service.interfaces.PostingStatusService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class PostingStatusServiceImpl implements PostingStatusService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final PostingStatusRepository postingStatusRepository;
    private final MessageSource messageSource;

    @Override
    public List<PostingStatus> getAllPostingStatuses() {
        return postingStatusRepository.findAll();
    }

    @Override
    public PostingStatus getPostingStatusByName(String name) {
        return postingStatusRepository.findByName(name);
    }

    @Override
    public PostingStatus getPostingStatusById(Long id) {
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

    @Override
    public PostingStatus localizeDescription(PostingStatus postingStatus, Locale locale) {

        postingStatus.setDescription_single(
                ((CustomMessageSource) messageSource).getMessages(locale)
                        .getProperty(
                                new StringBuilder(postingStatus.getClass().getSimpleName())
                                        .append(".")
                                        .append(postingStatus.getName())
                                        .append(".description_single")
                                        .toString()));

        postingStatus.setDescription_many(
                ((CustomMessageSource) messageSource).getMessages(locale)
                        .getProperty(
                                new StringBuilder(postingStatus.getClass().getSimpleName())
                                        .append(".")
                                        .append(postingStatus.getName())
                                        .append(".description_many")
                                        .toString()));

        return postingStatus;
    }

    @Override
    public List<PostingStatusStatistics> localizeDescription(List<PostingStatusStatistics> postingStatusStatisticsList, Locale locale) {
        for (PostingStatusStatistics postingStatusStatistics : postingStatusStatisticsList) {
            localizeDescription(postingStatusStatistics.getStatus(), locale);
        }
        return postingStatusStatisticsList;
    }
}
