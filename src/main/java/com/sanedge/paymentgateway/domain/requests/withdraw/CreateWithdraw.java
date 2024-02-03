package com.sanedge.paymentgateway.domain.requests.withdraw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWithdraw {
    private Long userId;
    private Integer withdrawAmount;
}
