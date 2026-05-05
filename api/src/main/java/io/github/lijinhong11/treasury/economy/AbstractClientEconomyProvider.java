package io.github.lijinhong11.treasury.economy;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class AbstractClientEconomyProvider implements EconomyProvider {
    @Override
    public EconomyResponse withdraw(UUID player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse withdraw(String player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse withdraw(UUID player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse withdraw(String player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse deposit(UUID player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse deposit(UUID player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse deposit(String player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse deposit(String player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse transfer(UUID from, UUID to, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse transfer(UUID from, UUID to, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse transfer(String from, String to, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public EconomyResponse transfer(String from, String to, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public void setBalance(UUID player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public void setBalance(UUID player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public void setBalance(String player, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }

    @Override
    public void setBalance(String player, Currency currency, BigDecimal amount) {
        throw new UnsupportedOperationException("Client operations aren't allowed");
    }
}
