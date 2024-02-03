package com.sanedge.paymentgateway.service;

import java.util.Optional;

import com.sanedge.paymentgateway.models.RefreshToken;
import com.sanedge.paymentgateway.models.User;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    Optional<RefreshToken> findByUser(User user);

    int deleteByUserId(Long userId);

    RefreshToken updateExpiryDate(RefreshToken refreshToken);
}
