package com.avito.service.interfaces;


import com.avito.models.User;
import com.avito.models.posting.Posting;

import java.util.List;

public interface UserService {
        List<User> getAllUsers ();
        User findUserByLogin (String login);
        User addUser (User user);
        User updateUser (User user);
        void deleteUser (long id);
        Posting addFavoritePosting(Long id);
        void deleteFavoritePosting(Long id);
}
