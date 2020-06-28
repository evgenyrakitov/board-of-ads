package com.avito.email_service.dao_email;

import com.avito.email_service.dao_email.PasswordResetTokenRepository;
import com.avito.email_service.model_email.PasswordResetToken;
import com.avito.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class PasswordResetTokenRepositoryIml implements PasswordResetTokenRepository {

    private final EntityManager entityManager;

    @Override
    public PasswordResetToken findByToken(String token) {
        PasswordResetToken passwordResetToken = null;
            passwordResetToken = entityManager.createQuery(
                    "from PasswordResetToken where token =: token",
                    PasswordResetToken.class)
                    .setParameter("token", token)
                    .getSingleResult();
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken findByUser(User user) {
        PasswordResetToken passwordResetToken = null;
        passwordResetToken = entityManager.createQuery(
                "from PasswordResetToken p where p.user =: token",
                PasswordResetToken.class)
                .setParameter("user", user)
                .getSingleResult();
        return passwordResetToken;
    }

    @Override
    public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now) {
        return null;
    }

    @Override
    public void deleteByExpiryDateLessThan(Date now) {

    }

    @Override
    public void deleteAllExpiredSince(Date now) {

    }
}
