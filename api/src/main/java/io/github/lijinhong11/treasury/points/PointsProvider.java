package io.github.lijinhong11.treasury.points;

import java.util.UUID;

/**
 * <p>
 *     Represents a points system implementation.<br>
 *     Points is usually used as an advanced currency for servers.
 * </p>
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
    String format(double amount);

    /**
     * Sets player points balance.
     *
     * @param player player uuid
     * @param amount new balance
     */
    void setBalance(UUID player, double amount);

    /**
     * Sets player points balance.
     *
     * @param player player name
     * @param amount new balance
     */
    void setBalance(String player, double amount);

    /**
     * Adds points to player account.
     *
     * @param player player uuid
     * @param amount amount to add
     */
    void deposit(UUID player, double amount);

    /**
     * Adds points to player account.
     *
     * @param player player name
     * @param amount amount to add
     */
    void deposit(String player, double amount);

    /**
     * Removes points from player account.
     *
     * @param player player uuid
     * @param amount amount to remove
     * @return true if success
     */
    boolean withdraw(UUID player, double amount);

    /**
     * Removes points from player account.
     *
     * @param player player name
     * @param amount amount to remove
     * @return true if success
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
}
