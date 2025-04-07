package com.sant.hexagonalbankingsystem.infrastructure.persistence;

import com.sant.hexagonalbankingsystem.domain.model.Account;
import com.sant.hexagonalbankingsystem.domain.ports.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository jpaRepository;

    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private AccountEntity toEntity(Account account) {
        return new AccountEntity(account.getId(), account.getOwnerName(), account.getBalance());
    }

    private Account toDomain(AccountEntity entity) {
        return new Account(entity.getId(), entity.getOwnerName(), entity.getBalance());
    }
}
