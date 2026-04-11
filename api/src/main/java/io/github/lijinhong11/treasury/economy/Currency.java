package io.github.lijinhong11.treasury.economy;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An immutable currency definition used by economy providers.<br>
 * Default maximum balance is {@link Long#MAX_VALUE} <br>
 * Mods shouldn't try to pass it!
 */
public record Currency(String id, String singularName, String pluralName, BigDecimal minBalance, BigDecimal startingBalance, BigDecimal maxBalance) {
    /**
     * Create a currency
     *
     * @param id unique currency id inside a provider
     * @param singularName singular display name
     * @param pluralName plural display name
     */
    public Currency(String id, String singularName, String pluralName) {
        this(id, singularName, pluralName, new BigDecimal(0), new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }

    /**
     * Create a currency
     *
     * @param id unique currency id inside a provider
     * @param singularName singular display name
     * @param pluralName plural display name
     * @param minBalance minimum balance
     */
    public Currency(String id, String singularName, String pluralName, BigDecimal minBalance) {
        this(id, singularName, pluralName, minBalance, new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }

    /**
     * Create a currency
     *
     * @param id unique currency id inside a provider
     * @param singularName singular display name
     * @param pluralName plural display name
     * @param minBalance minimum balance
     * @param startingBalance the balance when new players enter the game
     */
    public Currency(String id, String singularName, String pluralName, BigDecimal minBalance, BigDecimal startingBalance) {
        this(id, singularName, pluralName, minBalance, startingBalance, new BigDecimal(Long.MAX_VALUE));
    }

    /**
     * Create a currency
     *
     * @param id unique currency id inside a provider
     * @param singularName singular display name
     * @param pluralName plural display name
     * @param minBalance minimum balance
     * @param startingBalance the balance when new players enter the game
     * @param maxBalance maximum balance
     */
    public Currency {
        id = requireText(id, "currency id mustn't be blank");
        singularName = requireText(singularName, "currency singular name mustn't be blank");
        pluralName = requireText(pluralName, "currency plural name mustn't be blank");
        Objects.requireNonNull(startingBalance, "starting balance mustn't be null");
    }

    /**
     * Gets the currency name through amount
     *
     * @param amount the amount
     * @return the currency name
     */
    public String name(double amount) {
        return Math.abs(amount) == 1D ? singularName : pluralName;
    }

    public boolean is(String id) {
        return this.id.equals(Objects.requireNonNull(id, "currency id mustn't be null").trim());
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);

        String trimmed = value.trim();

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return trimmed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Currency currency)) {
            return false;
        }

        return id.equals(currency.id) &&
                singularName.equals(currency.singularName) &&
                pluralName.equals(currency.pluralName) &&
                startingBalance.equals(currency.startingBalance);
    }
}
