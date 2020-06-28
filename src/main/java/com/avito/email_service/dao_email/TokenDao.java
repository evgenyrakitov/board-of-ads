package com.avito.email_service.dao_email;

import com.avito.email_service.model_email.PasswordResetToken;

public interface TokenDao {
 void addPasswordToken(PasswordResetToken passwordResetToken);
}
