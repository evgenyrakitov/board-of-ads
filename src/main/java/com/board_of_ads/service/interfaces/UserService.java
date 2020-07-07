package com.board_of_ads.service.interfaces;


import com.board_of_ads.models.PasswordResetToken;
import com.board_of_ads.models.User;

import java.util.List;

public interface UserService {
        List<User> getAllUsers ();
        User findUserByEmail (String email);
        User addUser (User user);
        User updateUser (User user);
        void deleteUser (long id);
        User addFavoritePosting(Long id_posting, Long id_user);
        void deleteFavoritePosting(Long id_posting, Long id_user);
        void addPasswordToken(PasswordResetToken passwordResetToken);
        String validatePasswordResetToken(String token);
        void createPasswordResetTokenForUser(User user, String token);
        PasswordResetToken findByToken(String token);
}
