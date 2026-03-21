package io.github.lijinhong11.treasury;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents a economy system implementation.
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
     * Returns true if the given implementation supports banks.
     *
     * @return true if banks are supported
     */
    boolean supportBank();

    /**
     * Format amount into a human-readable String.
     *
     * @param amount amount to format
     * @return formatted amount
     */
    String format(double amount);

    /**
     * Returns plural currency name.
     *
     * @return plural currency name
     */
    String currencyNamePlural();

    /**
     * Returns singular currency name.
     *
     * @return singular currency name
     */
    String currencyNameSingular();

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
     * Gets player balance.
     *
     * @param player player name
     * @return balance
     */
    double getBalance(String player);

    /**
     * Sets player balance.
     *
     * @param player player uuid
     * @param amount new balance
     */
    void setBalance(UUID player, double amount);

    /**
     * Sets player balance.
     *
     * @param player player name
     * @param amount new balance
     */
    void setBalance(String player, double amount);

    /**
     * Adds money to player account.
     *
     * @param player player uuid
     * @param amount amount to add
     */
    void deposit(UUID player, double amount);

    /**
     * Adds money to player account.
     *
     * @param player player name
     * @param amount amount to add
     */
    void deposit(String player, double amount);

    /**
     * Removes money from player account.
     *
     * @param player player uuid
     * @param amount amount to remove
     * @return success
     */
    boolean withdraw(UUID player, double amount);

    /**
     * Removes money from player account.
     *
     * @param player player name
     * @param amount amount to remove
     * @return success
     */
    boolean withdraw(String player, double amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player uuid
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(UUID player, double amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player name
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(String player, double amount);

    /**
     * Transfers money between two players.
     *
     * @param from sender uuid
     * @param to receiver uuid
     * @param amount amount
     * @return true if the transfer is successful
     */
    boolean transfer(UUID from, UUID to, double amount);

    /**
     * Transfers money between two players.
     *
     * @param from sender name
     * @param to receiver name
     * @param amount amount
     * @return true if the transfer is successful
     */
    boolean transfer(String from, String to, double amount);

    /**
     * Checks if a bank with the given name exists.
     *
     * @param bank name of the bank
     * @return true if the bank exists
     */
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
     */
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
     */
    boolean createBank(String bank, String owner);

    /**
     * Deletes an existing bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return false.
     *
     * @param bank name of the bank to delete
     * @return true if the bank was successfully deleted
     */
    boolean deleteBank(String bank);

    /**
     * Gets the balance of the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, implementations should return 0.
     *
     * @param bank name of the bank
     * @return balance of the bank
     */
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
     */
    boolean bankHas(String bank, double amount);

    /**
     * Deposits money into the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, the deposit should not occur.
     *
     * @param bank name of the bank
     * @param amount amount to deposit
     */
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
     */
    boolean bankWithdraw(String bank, double amount);

    /**
     * Gets all banks
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, this method should return an empty collection.
     *
     * @return a collection of all banks
     */
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
     */
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
     */
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
     */
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
     */
    boolean bankRemoveMember(String bank, UUID player);

    /**
     * Gets all members of the specified bank.
     * <br>
     * If the economy implementation does not support banks or the bank
     * does not exist, an empty collection should be returned.
     *
     * @param bank name of the bank
     * @return collection of bank member UUIDs
     */
    Collection<UUID> bankMembers(String bank);

    /**
     * Creates a check issued by the specified player.
     * <br>
     * The check represents a transferable amount of currency that can
     * later be redeemed by another player.
     * <br>
     * Implementations may immediately withdraw the amount from the issuer
     * or defer validation until redemption depending on the economy design.
     *
     * @param issuer UUID of the check issuer
     * @param amount value of the check
     * @return created check
     */
    EconomyCheck createCheck(UUID issuer, double amount);

    /**
     * Checks if the specified check is valid.
     * <br>
     * This may include checks such as:
     * <ul>
     *     <li>Whether the check exists</li>
     *     <li>Whether it has already been redeemed</li>
     *     <li>Whether it has expired</li>
     * </ul>
     *
     * @param check check to validate
     * @return true if the check is valid
     */
    boolean isCheckValid(EconomyCheck check);
}