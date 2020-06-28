package com.avito.email_service.service_email;

import com.avito.email_service.model_email.PasswordResetToken;
import com.avito.models.User;

public interface IUserService {
    void createPasswordResetTokenForUser(User user, String token);
    PasswordResetToken findByToken(String token);
}
