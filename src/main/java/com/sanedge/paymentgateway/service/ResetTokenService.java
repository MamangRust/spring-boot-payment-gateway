package com.sanedge.paymentgateway.service;

import java.util.Optional;

import com.sanedge.paymentgateway.models.ResetToken;
import com.sanedge.paymentgateway.models.User;

public interface ResetTokenService {
    ResetToken createResetToken(User user);

    void deleteResetToken(Long userId);

    Optional<ResetToken> findByToken(String token);

    void updateExpiryDate(ResetToken resetToken);
}
