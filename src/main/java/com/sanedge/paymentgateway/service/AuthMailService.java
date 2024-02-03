package com.sanedge.paymentgateway.service;

public interface AuthMailService {
    void sendEmailVerify(String email, String token);

    void sendResetPasswordEmail(String email, String resetLink);
}
