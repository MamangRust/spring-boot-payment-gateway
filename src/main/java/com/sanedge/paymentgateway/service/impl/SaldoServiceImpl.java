package com.sanedge.paymentgateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.paymentgateway.domain.requests.saldo.CreateSaldoRequest;
import com.sanedge.paymentgateway.domain.requests.saldo.UpdateSaldoRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.exception.ResourceNotFoundException;
import com.sanedge.paymentgateway.models.Saldo;
import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.repository.SaldoRepository;
import com.sanedge.paymentgateway.repository.UserRepository;
import com.sanedge.paymentgateway.service.SaldoService;

@Service
public class SaldoServiceImpl implements SaldoService {
    private final SaldoRepository saldoRepository;
    private final UserRepository userRepository;

    @Autowired
    public SaldoServiceImpl(SaldoRepository saldoRepository, UserRepository userRepository) {
        this.saldoRepository = saldoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse CreateSaldo(CreateSaldoRequest request) {
        Saldo newSaldo = new Saldo();

        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        newSaldo.setTotalBalance(request.getTotalBalance());
        newSaldo.setUser(user);

        this.saldoRepository.save(newSaldo);

        return MessageResponse.builder().message("Success create saldo").statusCode(200).build();

    }

    @Override
    public MessageResponse GetSaldos() {
        List<Saldo> saldos = this.saldoRepository.findAll();

        if (saldos.isEmpty()) {
            return MessageResponse.builder().message("Saldos not found").statusCode(404).build();
        }

        return MessageResponse.builder().message("Success get saldos").data(saldos).statusCode(200).build();
    }

    @Override
    public MessageResponse GetSaldo(Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        return MessageResponse.builder().message("Success get saldo").data(saldo).statusCode(200).build();

    }

    @Override
    public MessageResponse UpdateSaldo(UpdateSaldoRequest request) {
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        saldo.setTotalBalance(request.getNewTotalBalance());
        saldo.setUser(user);

        this.saldoRepository.save(saldo);

        return MessageResponse.builder().message("Success update saldo").data(saldo).statusCode(200).build();
    }

    @Override
    public MessageResponse DeleteSaldo(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        this.saldoRepository.delete(saldo);

        return MessageResponse.builder().message("Success delete saldo").statusCode(200).build();
    }

}
