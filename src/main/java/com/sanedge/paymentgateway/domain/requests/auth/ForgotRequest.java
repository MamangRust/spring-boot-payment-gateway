package com.sanedge.paymentgateway.domain.requests.auth;

import lombok.Data;

@Data
public class ForgotRequest {
    private String email;
}
