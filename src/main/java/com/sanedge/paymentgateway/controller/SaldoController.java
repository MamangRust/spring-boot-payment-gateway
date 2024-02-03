package com.sanedge.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.paymentgateway.domain.requests.saldo.CreateSaldoRequest;
import com.sanedge.paymentgateway.domain.requests.saldo.UpdateSaldoRequest;
import com.sanedge.paymentgateway.domain.response.MessageResponse;
import com.sanedge.paymentgateway.service.AuthService;
import com.sanedge.paymentgateway.service.SaldoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/saldo")
public class SaldoController {

    private final SaldoService saldoService;
    private final AuthService authService;

    @Autowired
    public SaldoController(SaldoService saldoService, AuthService authService) {
        this.saldoService = saldoService;
        this.authService = authService;
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> CreateSaldo(@Valid @RequestBody CreateSaldoRequest request) {
        MessageResponse createSaldo = this.saldoService.CreateSaldo(request);

        return ResponseEntity.ok(createSaldo);

    }

    @GetMapping("/")
    public ResponseEntity<MessageResponse> GetSaldos() {
        MessageResponse getSaldos = this.saldoService.GetSaldos();

        return ResponseEntity.ok(getSaldos);
    }

    @GetMapping("/userid")
    public ResponseEntity<MessageResponse> GetSaldo() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse getSaldo = this.saldoService.GetSaldo(userId);

        return ResponseEntity.ok(getSaldo);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> UpdateSaldo(@Valid @RequestBody UpdateSaldoRequest request) {
        MessageResponse updateSaldo = this.saldoService.UpdateSaldo(request);

        return ResponseEntity.ok(updateSaldo);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> DeleteSaldo() {
        Long userId = this.authService.getCurrentUser().getUserId();

        MessageResponse deleteSaldo = this.saldoService.DeleteSaldo(userId);

        return ResponseEntity.ok(deleteSaldo);
    }
}
