package io.github.lijinhong11.treasury.economy;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represents an economy system implementation.
 */
public interface EconomyProvider {
    /**
     * Checks if economy implementation is enabled.
     *
     * @return true if enabled
     */
    boolean isEnabled();

    /**
     * Gets the unique name of economy implementation.
     *
     * @return the unique name of the economy implementation
     */
    String getName();

    /**
     * Returns the default currency exposed by this provider.
     * <p>
     * Legacy single-currency implementations do not need to override this.
     *
     * @return default currency
     */
    default Currency defaultCurrency() {
        return new Currency("default", currencyNameSingular(), currencyNamePlural());
    }

    /**
     * Returns all currencies exposed by this provider.
     * <p>
     * Single-currency implementations do not need to override this.
     *
     * @return supported currencies
     */
    default Collection<Currency> currencies() {
        return List.of(defaultCurrency());
    }

    /**
     * Gets a currency by id.
     * <p>
     * Single-currency implementations do not need to override this.
     *
     * @param key currency id
     * @return matched currency, or null if not found
     */
    default Currency getCurrency(String key) {
        if (key == null || key.isBlank()) {
            return null;
        }

        for (Currency currency : currencies()) {
            if (currency.is(key)) {
                return currency;
            }
        }

        return null;
    }

    /**
     * Checks whether the given currency is supported.
     * <p>
     * If the implementation doesn't support multi currency, it will always return false.
     *
     * @param currency currency to check
     * @return true if supported
     */
    default boolean supportsCurrency(Currency currency) {
        if (currency == null) {
            return true;
        }

        return getCurrency(currency.id()) != null;
    }

//    /**
//     * Returns true if the given implementation supports banks.
//     *
//     * @return true if banks are supported
//     */
//    boolean supportBank();

    /**
     * Formats an amount into a human-readable string.
     *
     * @param amount amount to format
     * @return formatted amount
     */
    String format(BigDecimal amount);

    /**
     * Formats an amount into a human-readable string using the given currency.
     *
     * @param currency currency to use
     * @param amount   amount to format
     * @return formatted amount
     */
    String format(Currency currency, BigDecimal amount);

    /**
     * Returns plural currency name.
     *
     * @return plural currency name
     */
    String currencyNamePlural();

    /**
     * Returns plural currency name for the given currency.
     *
     * @param currency currency to query
     * @return plural currency name
     */
    default String currencyNamePlural(Currency currency) {
        if (!supportsCurrency(currency)) {
            return currencyNamePlural();
        }

        return currency == null ? currencyNamePlural() : currency.pluralName();
    }

    /**
     * Returns singular currency name.
     *
     * @return singular currency name
     */
    String currencyNameSingular();

    /**
     * Returns singular currency name for the given currency.
     *
     * @param currency currency to query
     * @return singular currency name
     */
    default String currencyNameSingular(Currency currency) {
        if (!supportsCurrency(currency)) {
            return currencyNameSingular();
        }

        return currency == null ? currencyNameSingular() : currency.singularName();
    }

    /**
     * Checks if player account exists.
     *
     * @param player player uuid
     * @return true if account exists
     */
    boolean hasAccount(UUID player);

    /**
     * Checks if player account exists.
     *
     * @param player player name
     * @return true if account exists
     */
    boolean hasAccount(String player);

    /**
     * Gets player balance.
     *
     * @param player player uuid
     * @return balance
     */
    BigDecimal getBalance(UUID player);

    /**
     * Gets player balance for the given currency.
     *
     * @param player   player uuid
     * @param currency currency to query
     * @return balance
     */
    BigDecimal getBalance(UUID player, Currency currency);

    /**
     * Gets player balance.
     *
     * @param player player name
     * @return balance
     */
    BigDecimal getBalance(String player);

    /**
     * Gets player balance for the given currency.
     *
     * @param player   player name
     * @param currency currency to query
     * @return balance
     */
    BigDecimal getBalance(String player, Currency currency);

    /**
     * Sets player balance.
     *
     * @param player player uuid
     * @param amount new balance
     */
    void setBalance(UUID player, BigDecimal amount);

    /**
     * Sets player balance for the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   new balance
     */
    void setBalance(UUID player, Currency currency, BigDecimal amount);

    /**
     * Sets player balance.
     *
     * @param player player name
     * @param amount new balance
     */
    void setBalance(String player, BigDecimal amount);

    /**
     * Sets player balance for the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   new balance
     */
    void setBalance(String player, Currency currency, BigDecimal amount);

    /**
     * Adds money to player account.
     *
     * @param player player uuid
     * @param amount amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(UUID player, BigDecimal amount);

    /**
     * Adds money to player account using the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(UUID player, Currency currency, BigDecimal amount);

    /**
     * Adds money to player account.
     *
     * @param player player name
     * @param amount amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(String player, BigDecimal amount);

    /**
     * Adds money to player account using the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(String player, Currency currency, BigDecimal amount);

    /**
     * Removes money from player account.
     *
     * @param player player uuid
     * @param amount amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(UUID player, BigDecimal amount);

    /**
     * Removes money from player account using the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(UUID player, Currency currency, BigDecimal amount);

    /**
     * Removes money from player account.
     *
     * @param player player name
     * @param amount amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(String player, BigDecimal amount);

    /**
     * Removes money from player account using the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(String player, Currency currency, BigDecimal amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player uuid
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(UUID player, BigDecimal amount);

    /**
     * Checks if player has at least amount in the given currency.
     *
     * @param player   player uuid
     * @param currency currency to query
     * @param amount   amount required
     * @return true if player has enough
     */
    boolean has(UUID player, Currency currency, BigDecimal amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player name
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(String player, BigDecimal amount);

    /**
     * Checks if player has at least amount in the given currency.
     *
     * @param player   player name
     * @param currency currency to query
     * @param amount   amount required
     * @return true if player has enough
     */
    boolean has(String player, Currency currency, BigDecimal amount);

    /**
     * Transfers money between two players.
     *
     * @param from   sender uuid
     * @param to     receiver uuid
     * @param amount amount
     * @return response about the transfer
     */
    EconomyResponse transfer(UUID from, UUID to, BigDecimal amount);

    /**
     * Transfers money between two players using the given currency.
     *
     * @param from     sender uuid
     * @param to       receiver uuid
     * @param currency currency to use
     * @param amount   amount
     * @return response about the transfer
     */
    EconomyResponse transfer(UUID from, UUID to, Currency currency, BigDecimal amount);

    /**
     * Transfers money between two players.
     *
     * @param from   sender name
     * @param to     receiver name
     * @param amount amount
     * @return response about the transfer
     */
    EconomyResponse transfer(String from, String to, BigDecimal amount);

    /**
     * Transfers money between two players using the given currency.
     *
     * @param from     sender name
     * @param to       receiver name
     * @param currency currency to use
     * @param amount   amount
     * @return response about the transfer
     */
    EconomyResponse transfer(String from, String to, Currency currency, BigDecimal amount);
}

/*
 * Bank-related APIs are intentionally left out for now.
 * Reintroduce them as real interface methods when bank support is implemented.
 */
