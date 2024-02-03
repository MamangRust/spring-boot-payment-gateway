package com.sanedge.paymentgateway.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.paymentgateway.models.ResetToken;
import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.repository.ResetTokenRepository;
import com.sanedge.paymentgateway.service.ResetTokenService;

@Service
public class ResetTokenImplService implements ResetTokenService {
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Override
    public ResetToken createResetToken(User user) {
        ResetToken resetToken = new ResetToken();
        resetToken.setUser(user);
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setExpiryDate(Instant.now().plus(24, ChronoUnit.HOURS)); // Token expiry in 24 hours
        return resetTokenRepository.save(resetToken);
    }

    @Override
    public void deleteResetToken(Long userId) {
        resetTokenRepository.deleteByUserId(userId);
    }

    @Override
    public Optional<ResetToken> findByToken(String token) {
        return resetTokenRepository.findByToken(token);
    }

    @Override
    public void updateExpiryDate(ResetToken resetToken) {
        resetToken.setExpiryDate(Instant.now().plus(24, ChronoUnit.HOURS));
        resetTokenRepository.save(resetToken);
    }
}
