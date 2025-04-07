package com.sant.hexagonalbankingsystem.infrastructure.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String ownerName;
    private BigDecimal initialBalance;
}
