package com.sant.hexagonalbankingsystem.application.service;

import com.sant.hexagonalbankingsystem.domain.model.Account;
import com.sant.hexagonalbankingsystem.domain.ports.AccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    private AccountRepositoryPort repository;
    private TransferService service;

    private UUID fromId;
    private UUID toId;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepositoryPort.class);
        service = new TransferService(repository);
        fromId = UUID.randomUUID();
        toId = UUID.randomUUID();
    }

    @Test
    void transferShouldMoveMoney() {
        Account from = new Account(fromId, "Alice", new BigDecimal("100.00"));
        Account to = new Account(toId, "Bob", new BigDecimal("50.00"));

        when(repository.findById(fromId)).thenReturn(Optional.of(from));
        when(repository.findById(toId)).thenReturn(Optional.of(to));

        service.transfer(fromId, toId, new BigDecimal("25.00"));

        assertEquals(new BigDecimal("75.00"), from.getBalance());
        assertEquals(new BigDecimal("75.00"), to.getBalance());
        verify(repository, times(2)).save(any());
    }
}