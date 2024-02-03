package com.sanedge.paymentgateway.domain.requests.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferRequest {
    private Long transferFrom;
    private Long transferTo;
    private Integer transferAmount;
}
