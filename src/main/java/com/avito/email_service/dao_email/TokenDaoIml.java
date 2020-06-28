package com.avito.email_service.dao_email;

import com.avito.email_service.dao_email.TokenDao;
import com.avito.email_service.model_email.PasswordResetToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class TokenDaoIml implements TokenDao {

    private final EntityManager entityManager;

    @Override
    public void addPasswordToken(PasswordResetToken passwordResetToken) {
        entityManager.persist(passwordResetToken);

    }
}
