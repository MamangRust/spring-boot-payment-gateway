package com.sanedge.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.paymentgateway.domain.requests.transfer.CreateTransferRequest;
import com.sanedge.paymentgateway.domain.requests.transfer.UpdateTransferRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.service.AuthService;
import com.sanedge.paymentgateway.service.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private final AuthService authService;
    private final TransferService transferService;

    @Autowired
    public TransferController(AuthService authService, TransferService transferService) {
        this.transferService = transferService;
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseEntity<MessageResponse> GetTransfers() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse transfers = this.transferService.GetTransfers(userId);

        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/userid")
    public ResponseEntity<MessageResponse> GetTransferUserId() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse transferUserId = this.transferService.GetTransferUserId(userId);

        return ResponseEntity.ok(transferUserId);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> CreateTransfer(@Valid @RequestBody CreateTransferRequest request) {
        MessageResponse transfer = this.transferService.CreateTransfer(request);

        return ResponseEntity.ok(transfer);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> UpdateTransfer(@Valid @RequestBody UpdateTransferRequest request) {
        MessageResponse transfer = this.transferService.UpdateTransfer(request);

        return ResponseEntity.ok(transfer);
    }

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> DeleteTransfer() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse deleteTransfer = this.transferService.DeleteTransfer(userId);

        return ResponseEntity.ok(deleteTransfer);
    }

}
