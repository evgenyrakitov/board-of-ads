package com.avito.email_service.securyti_email;

import com.avito.email_service.dao_email.PasswordResetTokenRepository;
import com.avito.email_service.model_email.PasswordResetToken;
import com.avito.email_service.securyti_email.ISecurityUserService;
import com.avito.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;

@Service
@AllArgsConstructor
public class UserSecurityService implements ISecurityUserService {

    private final PasswordResetTokenRepository resetTokenRepository;

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = resetTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }
    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

}
