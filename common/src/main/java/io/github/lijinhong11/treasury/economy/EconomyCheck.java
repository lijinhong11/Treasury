package io.github.lijinhong11.treasury.economy;

import java.util.UUID;

/**
 * Represents a check issued by an economy implementation.
 * <br>
 * A check is a transferable value container that can be redeemed
 * by a player to receive the stored amount of currency.
 * <br>
 * Implementations are responsible for handling validation,
 * duplication protection and redemption state.
 */
public interface EconomyCheck {
    /**
     * Gets the unique identifier of this check.
     * <br>
     * This should be globally unique to prevent duplication
     * or multiple redemptions.
     *
     * @return check unique id
     */
    UUID getId();

    /**
     * Gets the monetary value of this check.
     *
     * @return value stored in the check
     */
    double getValue();

    /**
     * Gets the issuer of this check.
     * <br>
     * The issuer is typically the player or system that created
     * the check.
     *
     * @return issuer UUID
     */
    UUID getIssuer();

    /**
     * Checks whether this check has already been redeemed.
     *
     * @return true if already redeemed
     */
    boolean isRedeemed();

    /**
     * Marks this check as redeemed.
     * <br>
     * Implementations should ensure this cannot be called
     * multiple times.
     */
    void redeem();

    /**
     * Gets the creation timestamp of this check.
     * <br>
     * The time is represented as epoch milliseconds.
     *
     * @return creation time
     */
    long getCreatedAt();
}