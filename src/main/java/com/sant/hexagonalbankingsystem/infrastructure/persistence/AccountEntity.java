package com.sant.hexagonalbankingsystem.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String ownerName;
    private BigDecimal balance;
}
