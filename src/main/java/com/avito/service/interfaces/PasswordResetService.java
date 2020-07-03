package com.avito.service.interfaces;

import com.avito.models.User;
import com.avito.models.reset_password.PasswordResetToken;

public interface PasswordResetService {
    void addPasswordToken(PasswordResetToken passwordResetToken);
    String validatePasswordResetToken(String token);
    void createPasswordResetTokenForUser(User user, String token);
    PasswordResetToken findByToken(String token);
}
