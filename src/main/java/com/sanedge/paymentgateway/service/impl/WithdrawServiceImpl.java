package com.sanedge.paymentgateway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.paymentgateway.domain.requests.withdraw.CreateWithdraw;
import com.sanedge.paymentgateway.domain.requests.withdraw.UpdateWithdraw;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.exception.ResourceNotFoundException;
import com.sanedge.paymentgateway.models.Saldo;
import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.models.Withdraw;
import com.sanedge.paymentgateway.repository.SaldoRepository;
import com.sanedge.paymentgateway.repository.UserRepository;
import com.sanedge.paymentgateway.repository.WithdrawRepository;
import com.sanedge.paymentgateway.service.WithdrawService;

@Service
public class WithdrawServiceImpl implements WithdrawService {
    private UserRepository userRepository;
    private SaldoRepository saldoRepository;
    private WithdrawRepository withdrawRepository;

    @Autowired
    public WithdrawServiceImpl(UserRepository userRepository, SaldoRepository saldoRepository,
            WithdrawRepository withdrawRepository) {
        this.userRepository = userRepository;
        this.saldoRepository = saldoRepository;
        this.withdrawRepository = withdrawRepository;
    }

    @Override
    public MessageResponse CreateWithdraw(CreateWithdraw request) {
        Withdraw withdraw = new Withdraw();

        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        if (saldo.getTotalBalance() < request.getWithdrawAmount()) {
            return MessageResponse.builder().message("Saldo not enough").statusCode(400).build();
        }

        saldo.setWithdrawAmount(request.getWithdrawAmount());

        Date currentDate = new Date();

        saldo.setWithdrawTime(currentDate);

        saldo.setTotalBalance(saldo.getTotalBalance() - request.getWithdrawAmount());

        this.saldoRepository.save(saldo);

        withdraw.setWithdrawAmount(request.getWithdrawAmount());

        withdraw.setWithdrawTime(currentDate);

        withdraw.setUser(user);

        this.withdrawRepository.save(withdraw);

        return MessageResponse.builder().message("Success create withdraw").data(withdraw).statusCode(200).build();

    }

    @Override
    public MessageResponse GetWithdrawId(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Withdraw withdraw = this.withdrawRepository.findUserId(user).orElseThrow(
                () -> new ResourceNotFoundException("Withdraw not found"));

        return MessageResponse.builder().message("Success get withdraw").data(withdraw).statusCode(200).build();
    }

    @Override
    public MessageResponse GetWithdraws(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Withdraw> withdraws = this.withdrawRepository.findUser(user);

        if (withdraws.isEmpty()) {
            return MessageResponse.builder().message("Withdraw not found").statusCode(400).build();
        }

        return MessageResponse.builder().message("Success get withdraws").data(withdraws).statusCode(200).build();
    }

    @Override
    public MessageResponse UpdateWithdraw(UpdateWithdraw request) {
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Withdraw withdraw = this.withdrawRepository.findUserId(user).orElseThrow(
                () -> new ResourceNotFoundException("Withdraw not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        withdraw.setWithdrawAmount(request.getWithdrawAmount());

        this.withdrawRepository.save(withdraw);

        saldo.setTotalBalance(saldo.getTotalBalance() + withdraw.getWithdrawAmount());

        this.saldoRepository.save(saldo);

        return MessageResponse.builder().message("Success update withdraw").data(withdraw).statusCode(200).build();
    }

    @Override
    public MessageResponse DeleteWithdraw(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Withdraw withdraw = this.withdrawRepository.findUserId(user).orElseThrow(
                () -> new ResourceNotFoundException("Withdraw not found"));

        this.withdrawRepository.delete(withdraw);

        return MessageResponse.builder().message("Success delete withdraw").statusCode(200).build();
    }

}
