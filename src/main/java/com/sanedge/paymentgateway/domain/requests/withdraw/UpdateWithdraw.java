package com.sanedge.paymentgateway.domain.requests.withdraw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWithdraw {
    private Long userId;
    private Integer withdrawAmount;
}
