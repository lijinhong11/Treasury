package io.github.lijinhong11.treasury.points;

import java.util.UUID;

/**
 * Represents a points system implementation.<br>
 * Points is usually used as an advanced currency for servers.<br>
 *
 * Maximum balance is {@link Integer#MAX_VALUE}
 */
public interface PointsProvider {
    /**
     * Checks if points implementation is enabled.
     *
     * @return true if enabled
     */
    boolean isEnabled();

    /**
     * Gets the unique name of points implementation.
     *
     * @return the unique name of the points implementation
     */
    String getName();

    /**
     * Format amount into a human-readable String.
     *
     * @param amount amount to format
     * @return formatted amount
     */
    String format(int amount);

    /**
     * Gets player points balance.
     *
     * @param player player uuid
     */
    int getBalance(UUID player);

    /**
     * Gets player points balance.
     *
     * @param player player name
     */
    int getBalance(String player);

    /**
     * Sets player points balance.
     *
     * @param player player uuid
     * @param amount new balance
     */
    void setBalance(UUID player, int amount);

    /**
     * Sets player points balance.
     *
     * @param player player name
     * @param amount new balance
     */
    void setBalance(String player, int amount);

    /**
     * Adds points to player account.
     *
     * @param player player uuid
     * @param amount amount to add
     */
    void deposit(UUID player, int amount);

    /**
     * Adds points to player account.
     *
     * @param player player name
     * @param amount amount to add
     */
    void deposit(String player, int amount);

    /**
     * Removes points from player account.
     *
     * @param player player uuid
     * @param amount amount to remove
     * @return true if success
     */
    boolean withdraw(UUID player, int amount);

    /**
     * Removes points from player account.
     *
     * @param player player name
     * @param amount amount to remove
     * @return true if success
     */
    boolean withdraw(String player, int amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player uuid
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(UUID player, int amount);

    /**
     * Checks if player has at least amount.
     *
     * @param player player name
     * @param amount amount required
     * @return true if player has enough
     */
    boolean has(String player, int amount);
}
