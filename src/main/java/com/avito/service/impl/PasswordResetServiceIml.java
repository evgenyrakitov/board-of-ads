package com.avito.service.impl;

import com.avito.models.User;
import com.avito.models.reset_password.PasswordResetToken;
import com.avito.repository.PasswordResetTokenRepository;
import com.avito.service.interfaces.PasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@AllArgsConstructor
@Service
@Transactional
public class PasswordResetServiceIml implements PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void addPasswordToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }
    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
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
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        addPasswordToken(myToken);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
