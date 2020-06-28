package com.avito.email_service.securyti_email;

public interface ISecurityUserService {
    String validatePasswordResetToken(String token);
}
