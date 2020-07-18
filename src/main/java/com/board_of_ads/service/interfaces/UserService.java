package com.board_of_ads.service.interfaces;


import com.board_of_ads.models.PasswordResetToken;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;

import java.util.List;
import java.util.Set;

public interface UserService {
        List<User> getAllUsers ();
        User findUserByEmail (String email);
        User addUser (User user);
        User updateUser (User user);
        void deleteUser (long id);
        void addFavoritePosting(Long id_posting, Long id_user);
        void deleteFavoritePosting(Long id_posting, Long id_user);
        void deleteAllFavoritePosting(Long id_user);
        Set<Posting> getFavoritePostings(Long userId);
        void addPasswordToken(PasswordResetToken passwordResetToken);
        String validatePasswordResetToken(String token);
        void createPasswordResetTokenForUser(User user, String token);
        PasswordResetToken findByToken(String token);

        void deletePasswordChangeToken(String token);
}
