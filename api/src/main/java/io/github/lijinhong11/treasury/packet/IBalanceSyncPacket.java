package io.github.lijinhong11.treasury.packet;

import io.github.lijinhong11.treasury.economy.EconomyProvider;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface IBalanceSyncPacket {
    UUID player();

    /**
     * Gets the economy provider
     * @return the economy provider
     */
    EconomyProvider provider();

    /**
     * Gets the balances of each currency
     * @return an unmodifiable map
     */
    Map<String, BigDecimal> balances();
}
