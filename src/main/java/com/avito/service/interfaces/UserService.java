package com.avito.service.interfaces;


import com.avito.models.User;
import com.avito.models.posting.Posting;

import java.util.List;

public interface UserService {
        List<User> getAllUsers ();
        User findUserByEmail (String email);
        User addUser (User user);
        User updateUser (User user);
        void deleteUser (long id);
        User addFavoritePosting(Long id_posting, Long id_user);
        void deleteFavoritePosting(Long id_posting, Long id_user);
}
