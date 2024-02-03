package com.sanedge.paymentgateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.paymentgateway.models.ResetToken;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    void deleteByUserId(Long userId);

    Optional<ResetToken> findByToken(String token);
}
