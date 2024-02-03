package com.sanedge.paymentgateway.service;

import com.sanedge.paymentgateway.domain.requests.withdraw.CreateWithdraw;
import com.sanedge.paymentgateway.domain.requests.withdraw.UpdateWithdraw;
import com.sanedge.paymentgateway.domain.response.MessageResponse;

public interface WithdrawService {
    public MessageResponse CreateWithdraw(CreateWithdraw request);

    public MessageResponse GetWithdrawId(Long userId);

    public MessageResponse GetWithdraws(Long userId);

    public MessageResponse UpdateWithdraw(UpdateWithdraw request);

    public MessageResponse DeleteWithdraw(Long userId);
}
