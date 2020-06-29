package com.avito.service.impl;

import com.avito.dao.interfaces.PostingDao;
import com.avito.dao.interfaces.UserDao;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.PostingService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final Logger logger = LoggerFactory.getLogger(PostingServiceImpl.class);

    private final PostingDao postingDao;

    @Override
    public Posting findPostingById(Long id) {
        return postingDao.findPostingById(id);
    }

}
