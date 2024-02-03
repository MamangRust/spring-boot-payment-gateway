package com.sanedge.paymentgateway.domain.requests.topup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTopupRequest {
    private Long userId;
    private Integer topupAmount;
    private String topupMethod;
}
