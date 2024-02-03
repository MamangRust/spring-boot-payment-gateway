package com.sanedge.paymentgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.paymentgateway.models.Transfer;
import com.sanedge.paymentgateway.models.User;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Optional<Transfer> findUserId(User user);

    List<Transfer> findUser(User user);
}
