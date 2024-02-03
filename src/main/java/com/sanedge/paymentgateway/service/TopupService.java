package com.sanedge.paymentgateway.service;

import com.sanedge.paymentgateway.domain.requests.topup.CreateTopupRequest;
import com.sanedge.paymentgateway.domain.requests.topup.UpdateTopupRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;

public interface TopupService {
    public MessageResponse CreateTopup(CreateTopupRequest request);

    public MessageResponse GetTopup(Long id);

    public MessageResponse GetTopups(Long userId);

    public MessageResponse GetTopupUserId(Long userId);

    public MessageResponse UpdateTopupUserId(UpdateTopupRequest request);

    public MessageResponse DeleteTopup(Long userId);
}
