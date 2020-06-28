package com.avito.email_service.service_email;

import com.avito.email_service.dao_email.PasswordResetTokenRepository;
import com.avito.email_service.dao_email.TokenDao;
import com.avito.email_service.model_email.PasswordResetToken;
import com.avito.email_service.service_email.IUserService;
import com.avito.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class IUserServiceIml implements IUserService {
    private final TokenDao tokenDao;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
         PasswordResetToken myToken = new PasswordResetToken(token, user);
        tokenDao.addPasswordToken(myToken);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
