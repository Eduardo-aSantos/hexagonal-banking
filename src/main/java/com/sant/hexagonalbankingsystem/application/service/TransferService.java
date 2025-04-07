package com.sant.hexagonalbankingsystem.application.service;

import com.sant.hexagonalbankingsystem.domain.model.Account;
import com.sant.hexagonalbankingsystem.domain.ports.AccountRepositoryPort;
import com.sant.hexagonalbankingsystem.domain.ports.TransferServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService implements TransferServicePort {

    private final AccountRepositoryPort repository;

    @Override
    public void transfer(UUID fromId, UUID toId, BigDecimal amount) {
        Account from = repository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("Origin account not found"));
        Account to = repository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        from.withdraw(amount);
        to.deposit(amount);

        repository.save(from);
        repository.save(to);
    }
}
