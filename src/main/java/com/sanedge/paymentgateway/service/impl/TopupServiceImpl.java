package com.sanedge.paymentgateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.paymentgateway.domain.requests.topup.CreateTopupRequest;
import com.sanedge.paymentgateway.domain.requests.topup.UpdateTopupRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.exception.ResourceNotFoundException;
import com.sanedge.paymentgateway.models.Saldo;
import com.sanedge.paymentgateway.models.Topup;
import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.repository.SaldoRepository;
import com.sanedge.paymentgateway.repository.TopupRepository;
import com.sanedge.paymentgateway.repository.UserRepository;
import com.sanedge.paymentgateway.service.TopupService;
import com.sanedge.paymentgateway.utils.PaymentMethod;

@Service
public class TopupServiceImpl implements TopupService {
    private final UserRepository userRepository;
    private final SaldoRepository saldoRepository;
    private final TopupRepository topupRepository;

    @Autowired
    public TopupServiceImpl(UserRepository userRepository, SaldoRepository saldoRepository,
            TopupRepository topupRepository) {
        this.userRepository = userRepository;
        this.saldoRepository = saldoRepository;
        this.topupRepository = topupRepository;
    }

    @Override
    public MessageResponse CreateTopup(CreateTopupRequest request) {
        if (request.getTopupAmount() > 50000) {
            return MessageResponse.builder().message("Topup amount must be less than 50000").statusCode(400).build();
        }

        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (PaymentMethod.isValidPaymentMethod(request.getTopupMethod())) {

            return MessageResponse.builder().message("Topup method not valid").statusCode(400).build();
        }

        Topup newTopup = new Topup();

        newTopup.setUser(user);
        newTopup.setTopUpAmount(request.getTopupAmount());
        newTopup.setTopUpMethod(request.getTopupMethod());

        this.topupRepository.save(newTopup);

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        saldo.setTotalBalance(saldo.getTotalBalance() + request.getTopupAmount());

        this.saldoRepository.save(saldo);

        return MessageResponse.builder().message("Success create topup").statusCode(200).build();
    }

    @Override
    public MessageResponse GetTopup(Long id) {
        Topup topup = this.topupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topup not found"));

        return MessageResponse.builder().message("Success get topup").data(topup).statusCode(200).build();
    }

    @Override
    public MessageResponse GetTopupUserId(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Topup topup = this.topupRepository.findByUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Topup not found"));

        return MessageResponse.builder().message("Success get topup").data(topup).statusCode(200).build();

    }

    @Override
    public MessageResponse UpdateTopupUserId(UpdateTopupRequest request) {
        if (request.getTopupAmount() > 50000) {
            return MessageResponse.builder().message("Topup amount must be less than 50000").statusCode(400).build();
        }

        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Topup topup = this.topupRepository.findByUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Topup not found"));

        Saldo saldo = this.saldoRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        topup.setTopUpAmount(request.getTopupAmount());
        topup.setTopUpMethod(request.getTopupMethod());

        this.topupRepository.save(topup);

        Integer balance = request.getTopupAmount() - topup.getTopUpAmount();

        saldo.setTotalBalance(saldo.getTotalBalance() + balance);

        this.saldoRepository.save(saldo);

        return MessageResponse.builder().message("Success update topup").data(topup).statusCode(200).build();
    }

    @Override
    public MessageResponse DeleteTopup(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Topup topup = this.topupRepository.findByUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Topup not found"));

        this.topupRepository.delete(topup);

        return MessageResponse.builder().message("Success delete topup").statusCode(200).build();
    }

    @Override
    public MessageResponse GetTopups(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Topup> topups = this.topupRepository.findByUser(user);

        if (topups.isEmpty()) {
            return MessageResponse.builder().message("Topups not found").statusCode(404).build();
        }

        return MessageResponse.builder().message("Success get topups").data(topups).statusCode(200).build();
    }
}
