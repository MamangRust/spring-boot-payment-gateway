package com.sanedge.paymentgateway.domain.requests.saldo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaldoRequest {
    private Long userId;
    private Integer totalBalance;
}