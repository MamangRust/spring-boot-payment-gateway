package com.sanedge.paymentgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.paymentgateway.models.Topup;
import com.sanedge.paymentgateway.models.User;

@Repository
public interface TopupRepository extends JpaRepository<Topup, Long> {
    List<Topup> findAll();

    List<Topup> findByUser(User user);

    Optional<Topup> findByUserId(User user);

}
