package com.sanedge.paymentgateway.service;

import com.sanedge.paymentgateway.domain.requests.saldo.CreateSaldoRequest;
import com.sanedge.paymentgateway.domain.requests.saldo.UpdateSaldoRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;

public interface SaldoService {
    public MessageResponse CreateSaldo(CreateSaldoRequest request);

    public MessageResponse GetSaldos();

    public MessageResponse GetSaldo(Long userId);

    public MessageResponse UpdateSaldo(UpdateSaldoRequest request);

    public MessageResponse DeleteSaldo(Long userId);
}
