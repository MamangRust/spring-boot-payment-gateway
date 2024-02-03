package com.sanedge.paymentgateway.service;

import com.sanedge.paymentgateway.domain.requests.auth.ForgotRequest;
import com.sanedge.paymentgateway.domain.requests.auth.LoginRequest;
import com.sanedge.paymentgateway.domain.requests.auth.RegisterRequest;
import com.sanedge.paymentgateway.domain.requests.auth.ResetPasswordRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.domain.response.auth.TokenRefreshResponse;
import com.sanedge.paymentgateway.models.User;

public interface AuthService {
    public MessageResponse login(LoginRequest loginRequest);

    public MessageResponse register(RegisterRequest registerRequest);

    public TokenRefreshResponse refreshToken(String refreshToken);

    User getCurrentUser();

    public MessageResponse logout();

    public MessageResponse forgotPassword(ForgotRequest request);

    public MessageResponse resetPassword(ResetPasswordRequest request);

    public MessageResponse verifyEmail(String token);
}
