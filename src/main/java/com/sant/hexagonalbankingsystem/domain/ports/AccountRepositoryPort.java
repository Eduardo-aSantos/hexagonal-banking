package com.sant.hexagonalbankingsystem.domain.ports;

import com.sant.hexagonalbankingsystem.domain.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findById(UUID id);
}

