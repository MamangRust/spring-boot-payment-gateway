package com.sanedge.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.paymentgateway.domain.requests.topup.CreateTopupRequest;
import com.sanedge.paymentgateway.domain.requests.topup.UpdateTopupRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.service.AuthService;
import com.sanedge.paymentgateway.service.TopupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/topup")
public class TopupController {
    private final TopupService topupService;
    private final AuthService authService;

    @Autowired
    public TopupController(TopupService topupService, AuthService authService) {
        this.topupService = topupService;
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseEntity<MessageResponse> GetTopups() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse topups = this.topupService.GetTopups(userId);

        return ResponseEntity.ok(topups);
    }

    @GetMapping("/userid")
    public ResponseEntity<MessageResponse> GetTopupUserId() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse topupUserId = this.topupService.GetTopupUserId(userId);

        return ResponseEntity.ok(topupUserId);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> CreateTopup(@Valid @RequestBody CreateTopupRequest request) {
        MessageResponse topup = this.topupService.CreateTopup(request);

        return ResponseEntity.ok(topup);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> UpdateTopup(@Valid @RequestBody UpdateTopupRequest request) {
        MessageResponse topup = this.topupService.UpdateTopupUserId(request);

        return ResponseEntity.ok(topup);
    }

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> DeleteTopup() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse deleteTopup = this.topupService.DeleteTopup(userId);

        return ResponseEntity.ok(deleteTopup);
    }

}
