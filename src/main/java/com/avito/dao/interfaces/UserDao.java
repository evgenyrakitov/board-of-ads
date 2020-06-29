package com.avito.dao.interfaces;

import com.avito.models.User;
import com.avito.models.posting.Posting;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers ();
    User findUserByLogin (String login);
    User addUser (User user);
    User updateUser (User user);
    void deleteUser (long id);
    Posting findPostingById (Long id);
}
