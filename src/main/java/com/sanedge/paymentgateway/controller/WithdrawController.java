package com.sanedge.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.paymentgateway.domain.requests.withdraw.CreateWithdraw;
import com.sanedge.paymentgateway.domain.requests.withdraw.UpdateWithdraw;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.service.AuthService;
import com.sanedge.paymentgateway.service.WithdrawService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController {
    private final WithdrawService withdrawService;
    private final AuthService authService;

    @Autowired
    public WithdrawController(WithdrawService withdrawService, AuthService authService) {
        this.withdrawService = withdrawService;
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseEntity<MessageResponse> GetWithdraws() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse withdraws = this.withdrawService.GetWithdraws(userId);

        return ResponseEntity.ok(withdraws);
    }

    @GetMapping("/userId")
    public ResponseEntity<MessageResponse> GetWithdraw() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse withdraws = this.withdrawService.GetWithdrawId(userId);

        return ResponseEntity.ok(withdraws);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> CreateWithdraw(@Valid @RequestBody CreateWithdraw request) {
        MessageResponse withdraw = this.withdrawService.CreateWithdraw(request);

        return ResponseEntity.ok(withdraw);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> UpdateWithdraw(@Valid @RequestBody UpdateWithdraw request) {
        MessageResponse updateWithdraw = this.withdrawService.UpdateWithdraw(request);

        return ResponseEntity.ok(updateWithdraw);
    }

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> DeleteWithdraw() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse deleteWithdraw = this.withdrawService.DeleteWithdraw(userId);

        return ResponseEntity.ok(deleteWithdraw);
    }
}
