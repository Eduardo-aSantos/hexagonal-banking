package com.sant.hexagonalbankingsystem.infrastructure.rest;

import com.sant.hexagonalbankingsystem.application.service.TransferService;
import com.sant.hexagonalbankingsystem.domain.model.Account;
import com.sant.hexagonalbankingsystem.domain.ports.AccountRepositoryPort;
import com.sant.hexagonalbankingsystem.infrastructure.rest.dto.CreateAccountRequest;
import com.sant.hexagonalbankingsystem.infrastructure.rest.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepositoryPort accountRepository;
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody CreateAccountRequest request) {
        Account account = new Account(UUID.randomUUID(), request.getOwnerName(), request.getInitialBalance());
        accountRepository.save(account);
        return ResponseEntity.created(URI.create("/api/accounts/" + account.getId())).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable UUID id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest request) {
        transferService.transfer(request.getFromId(), request.getToId(), request.getAmount());
        return ResponseEntity.ok().build();
    }
}