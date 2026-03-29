package io.github.lijinhong11.treasury.economy;

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
        return Currency.of("default", currencyNameSingular(), currencyNamePlural());
    }

    /**
     * Returns all currencies exposed by this provider.
     * <p>
     * Legacy single-currency implementations do not need to override this.
     *
     * @return supported currencies
     */
    default Collection<Currency> currencies() {
        return List.of(defaultCurrency());
    }

    /**
     * Gets a currency by key.
     *
     * @param key currency key
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
     *
     * @param currency currency to check
     * @return true if supported
     */
    default boolean supportsCurrency(Currency currency) {
        if (currency == null) {
            return true;
        }

        return getCurrency(currency.key()) != null;
    }

//    /**
//     * Returns true if the given implementation supports banks.
//     *
//     * @return true if banks are supported
//     */
//    boolean supportBank();

    /**
     * Format amount into a human-readable String.
     *
     * @param amount amount to format
     * @return formatted amount
     */
    String format(double amount);

    /**
     * Format amount into a human-readable String using the given currency.
     *
     * @param currency currency to use
     * @param amount   amount to format
     * @return formatted amount
     */
    String format(Currency currency, double amount);

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
    double getBalance(UUID player);

    /**
     * Gets player balance for the given currency.
     *
     * @param player   player uuid
     * @param currency currency to query
     * @return balance
     */
    double getBalance(UUID player, Currency currency);

    /**
     * Gets player balance.
     *
     * @param player player name
     * @return balance
     */
    double getBalance(String player);

    /**
     * Gets player balance for the given currency.
     *
     * @param player   player name
     * @param currency currency to query
     * @return balance
     */
    double getBalance(String player, Currency currency);

    /**
     * Sets player balance.
     *
     * @param player player uuid
     * @param amount new balance
     */
    void setBalance(UUID player, double amount);

    /**
     * Sets player balance for the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   new balance
     */
    void setBalance(UUID player, Currency currency, double amount);

    /**
     * Sets player balance.
     *
     * @param player player name
     * @param amount new balance
     */
    void setBalance(String player, double amount);

    /**
     * Sets player balance for the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   new balance
     */
    void setBalance(String player, Currency currency, double amount);

    /**
     * Adds money to player account.
     *
     * @param player player uuid
     * @param amount amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(UUID player, double amount);

    /**
     * Adds money to player account using the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(UUID player, Currency currency, double amount);

    /**
     * Adds money to player account.
     *
     * @param player player name
     * @param amount amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(String player, double amount);

    /**
     * Adds money to player account using the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   amount to add
     * @return response about the deposit
     */
    EconomyResponse deposit(String player, Currency currency, double amount);

    /**
     * Removes money from player account.
     *
     * @param player player uuid
     * @param amount amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(UUID player, double amount);

    /**
     * Removes money from player account using the given currency.
     *
     * @param player   player uuid
     * @param currency currency to use
     * @param amount   amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(UUID player, Currency currency, double amount);

    /**
     * Removes money from player account.
     *
     * @param player player name
     * @param amount amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(String player, double amount);

    /**
     * Removes money from player account using the given currency.
     *
     * @param player   player name
     * @param currency currency to use
     * @param amount   amount to remove
     * @return response about the withdrawal
     */
    EconomyResponse withdraw(String player, Currency currency, double amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player uuid
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(UUID player, double amount);

    /**
     * Checks if player has at least amount in the given currency.
     *
     * @param player   player uuid
     * @param currency currency to query
     * @param amount   amount required
     * @return true if player has enough
     */
    boolean has(UUID player, Currency currency, double amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player name
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(String player, double amount);

    /**
     * Checks if player has at least amount in the given currency.
     *
     * @param player   player name
     * @param currency currency to query
     * @param amount   amount required
     * @return true if player has enough
     */
    boolean has(String player, Currency currency, double amount);

    /**
     * Transfers money between two players.
     *
     * @param from   sender uuid
     * @param to     receiver uuid
     * @param amount amount
     * @return response about the transfer
     */
    EconomyResponse transfer(UUID from, UUID to, double amount);

    /**
     * Transfers money between two players using the given currency.
     *
     * @param from     sender uuid
     * @param to       receiver uuid
     * @param currency currency to use
     * @param amount   amount
     * @return response about the transfer
     */
    EconomyResponse transfer(UUID from, UUID to, Currency currency, double amount);

    /**
     * Transfers money between two players.
     *
     * @param from   sender name
     * @param to     receiver name
     * @param amount amount
     * @return response about the transfer
     */
    EconomyResponse transfer(String from, String to, double amount);

    /**
     * Transfers money between two players using the given currency.
     *
     * @param from     sender name
     * @param to       receiver name
     * @param currency currency to use
     * @param amount   amount
     * @return response about the transfer
     */
    EconomyResponse transfer(String from, String to, Currency currency, double amount);
}

    //BANK SECTION, CURRENTLY NOT USED

    /*
     * Checks if a bank with the given name exists.
     *
     * @param bank name of the bank
     * @return true if the bank exists

    boolean hasBank(String bank);

    /**
     * Creates a new bank with the specified owner.
     * <br>
     * If the economy implementation does not support banks, this method
     * should return false.
     *
     * @param bank name of the bank to create
     * @param owner UUID of the bank owner
     * @return true if the bank was successfully created

    boolean createBank(String bank, UUID owner);

    /**
     * Creates a new bank with the specified owner.
     * <br>
     * If the economy implementation does not support banks, this method
     * should return false.
     *
     * @param bank name of the bank to create
     * @param owner name of the bank owner
     * @return true if the bank was successfully created

    boolean createBank(String bank, String owner);

    /**
     * Deletes an existing bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     *
     * @param bank name of the bank to delete
     * @return true if the bank was successfully deleted

    boolean deleteBank(String bank);

    /**
     * Gets the balance of the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, implementations should return 0.
     *
     * @param bank name of the bank
     * @return balance of the bank

    double bankBalance(String bank);

    /**
     * Gets the balance of the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, implementations should return false.
     * <br>
     * If the bank has not the amount specified, implementations should
     * also return false.
     *
     * @param bank name of the bank
     * @return balance of the bank

    boolean bankHas(String bank, double amount);

    /**
     * Deposits money into the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, the deposit should not occur.
     *
     * @param bank name of the bank
     * @param amount amount to deposit

    void bankDeposit(String bank, double amount);

    /**
     * Withdraws money from the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     *
     * @param bank name of the bank
     * @param amount amount to withdraw
     * @return true if the withdrawal was successful

    boolean bankWithdraw(String bank, double amount);

    /**
     * Gets all banks
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return an empty collection.
     *
     * @return a collection of all banks

    Collection<String> banks();

    /**
     * Checks if the specified player is the owner of the bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     *
     * @param bank name of the bank
     * @param player UUID of the player
     * @return true if the player is the owner of the bank

    boolean isBankOwner(String bank, UUID player);

    /**
     * Checks if the specified player is a member of the bank.
     * <br>
     * Members may have access to the bank but are not necessarily the owner.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     *
     * @param bank name of the bank
     * @param player UUID of the player
     * @return true if the player is a member of the bank

    boolean isBankMember(String bank, UUID player);

    /**
     * Adds a player as a member of the specified bank.
     * <br>
     * Members may have access to perform certain operations on the bank
     * depending on the implementation, but are not necessarily the owner.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, the member should not be added and this method
     * should return false.
     *
     * @param bank name of the bank
     * @param player UUID of the player to add
     * @return true if the player was successfully added as a member

    boolean bankAddMember(String bank, UUID player);

    /**
     * Removes a player from the specified bank's member list.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     * <br>
     * If the player is not a member of the bank, implementations should
     * also return false.
     *
     * @param bank name of the bank
     * @param player UUID of the player to remove
     * @return true if the player was successfully removed

    boolean bankRemoveMember(String bank, UUID player);

    /**
     * Gets all members of the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, an empty collection should be returned.
     *
     * @param bank name of the bank
     * @return collection of bank member UUIDs

    Collection<UUID> bankMembers(String bank);


     */
