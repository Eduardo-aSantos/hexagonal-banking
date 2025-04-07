package com.sant.hexagonalbankingsystem.domain.ports;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransferServicePort {
    void transfer(UUID fromId, UUID toId, BigDecimal amount);
}
