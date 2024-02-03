package com.sanedge.paymentgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanedge.paymentgateway.models.Saldo;
import com.sanedge.paymentgateway.models.User;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    List<Saldo> findAll();

    Optional<Saldo> findByUser(User user);
}
