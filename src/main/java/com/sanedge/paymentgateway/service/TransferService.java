package com.sanedge.paymentgateway.service;

import com.sanedge.paymentgateway.domain.requests.transfer.CreateTransferRequest;
import com.sanedge.paymentgateway.domain.requests.transfer.UpdateTransferRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;

public interface TransferService {
    public MessageResponse CreateTransfer(CreateTransferRequest request);

    public MessageResponse GetTransferUserId(Long userId);

    public MessageResponse GetTransfers(Long userId);

    public MessageResponse UpdateTransfer(UpdateTransferRequest request);

    public MessageResponse DeleteTransfer(Long userId);
}
