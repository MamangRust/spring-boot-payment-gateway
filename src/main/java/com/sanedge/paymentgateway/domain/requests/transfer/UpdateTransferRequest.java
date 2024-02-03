package com.sanedge.paymentgateway.domain.requests.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransferRequest {
    private Long userId;
    private Integer transferAmount;
}
