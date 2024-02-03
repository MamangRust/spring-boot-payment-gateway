package com.sanedge.paymentgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.models.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    Optional<Withdraw> findUserId(User user);

    List<Withdraw> findUser(User user);
}
