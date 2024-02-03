package com.sanedge.paymentgateway.domain.requests.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
}
