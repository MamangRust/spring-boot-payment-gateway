package com.sanedge.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.paymentgateway.domain.requests.auth.LoginRequest;
import com.sanedge.paymentgateway.domain.requests.auth.RegisterRequest;
import com.sanedge.paymentgateway.domain.requests.auth.TokenRefreshRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.domain.response.auth.TokenRefreshResponse;
import com.sanedge.paymentgateway.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        MessageResponse authResponse = this.authService.login(loginRequest);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse authMessageResponse = this.authService.register(registerRequest);

        return new ResponseEntity<>(authMessageResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
