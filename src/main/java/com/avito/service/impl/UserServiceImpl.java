package com.avito.service.impl;

import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.repository.PostingRepository;
import com.avito.repository.UserRepository;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userUpdate = findUserByLogin(user.getUsername());
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(userUpdate);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addFavoritePosting(Long id_posting, Long id_user) {
        User user = userRepository.getOne(id_user);
        Posting posting = postingRepository.getOne(id_posting);
        user.getFavoritePostings().add(posting);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteFavoritePosting(Long id_posting, Long id_user) {
        User user = userRepository.getOne(id_user);
        Posting posting = postingRepository.getOne(id_posting);
        user.getFavoritePostings().remove(posting);
        userRepository.save(user);
    }
}
