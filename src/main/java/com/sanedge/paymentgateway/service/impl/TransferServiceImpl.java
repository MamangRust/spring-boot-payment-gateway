package com.sanedge.paymentgateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.paymentgateway.domain.requests.transfer.CreateTransferRequest;
import com.sanedge.paymentgateway.domain.requests.transfer.UpdateTransferRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.exception.ResourceNotFoundException;
import com.sanedge.paymentgateway.models.Saldo;
import com.sanedge.paymentgateway.models.Transfer;
import com.sanedge.paymentgateway.models.User;
import com.sanedge.paymentgateway.repository.SaldoRepository;
import com.sanedge.paymentgateway.repository.TransferRepository;
import com.sanedge.paymentgateway.repository.UserRepository;
import com.sanedge.paymentgateway.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final SaldoRepository saldoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, SaldoRepository saldoRepository,
            UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.saldoRepository = saldoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse CreateTransfer(CreateTransferRequest request) {
        if (request.getTransferAmount() > 50000) {
            return MessageResponse.builder().message("Transfer amount must be less than 50000").statusCode(400).build();
        }

        User sender = this.userRepository.findById(request.getTransferFrom())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        User receiver = this.userRepository.findById(request.getTransferTo())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = new Transfer();

        transfer.setTransferFrom(sender);
        transfer.setTransferTo(receiver);
        transfer.setTransferAmount(request.getTransferAmount());

        this.transferRepository.save(transfer);

        Saldo senderSaldo = this.saldoRepository.findByUser(sender)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        Saldo receiverSaldo = this.saldoRepository.findByUser(receiver)
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        senderSaldo.setTotalBalance(senderSaldo.getTotalBalance() - request.getTransferAmount());

        receiverSaldo.setTotalBalance(receiverSaldo.getTotalBalance() + request.getTransferAmount());

        this.saldoRepository.save(senderSaldo);

        this.saldoRepository.save(receiverSaldo);

        return MessageResponse.builder().message("Success create transfer").data(transfer).statusCode(200).build();
    }

    @Override
    public MessageResponse GetTransferUserId(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = this.transferRepository.findUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        return MessageResponse.builder().message("Success get transfer").data(transfer).statusCode(200).build();
    }

    @Override
    public MessageResponse GetTransfers(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Transfer> transfers = this.transferRepository.findUser(user);

        if (transfers.isEmpty()) {
            return MessageResponse.builder().message("No transfers found").statusCode(200).build();
        }

        return MessageResponse.builder().message("Success get transfers").data(transfers).statusCode(200).build();
    }

    @Override
    public MessageResponse UpdateTransfer(UpdateTransferRequest request) {
        if (request.getTransferAmount() > 50000) {
            return MessageResponse.builder().message("Transfer amount must be less than 50000").statusCode(400).build();
        }
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = this.transferRepository.findUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        transfer.setTransferAmount(request.getTransferAmount());

        Saldo senderSaldo = this.saldoRepository.findByUser(transfer.getTransferFrom())
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        Saldo receiverSaldo = this.saldoRepository.findByUser(transfer.getTransferTo())
                .orElseThrow(() -> new ResourceNotFoundException("Saldo not found"));

        senderSaldo.setTotalBalance(senderSaldo.getTotalBalance() - transfer.getTransferAmount());

        receiverSaldo.setTotalBalance(receiverSaldo.getTotalBalance() + transfer.getTransferAmount());

        this.saldoRepository.save(senderSaldo);

        this.saldoRepository.save(receiverSaldo);

        this.transferRepository.save(transfer);

        return MessageResponse.builder().message("Success update transfer").data(transfer).statusCode(200).build();
    }

    @Override
    public MessageResponse DeleteTransfer(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = this.transferRepository.findUserId(user)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        this.transferRepository.delete(transfer);

        return MessageResponse.builder().message("Success delete transfer").statusCode(200).build();
    }

}
