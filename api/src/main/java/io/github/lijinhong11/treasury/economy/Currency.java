package io.github.lijinhong11.treasury.economy;

import java.util.Objects;

/**
 * An immutable currency definition used by economy providers.
 *
 * @param id unique currency id inside a provider
 * @param singularName singular display name
 * @param pluralName plural display name
 */
public record Currency(String id, String singularName, String pluralName) {
    public Currency {
        id = requireText(id, "currency id mustn't be blank");
        singularName = requireText(singularName, "currency singular name mustn't be blank");
        pluralName = requireText(pluralName, "currency plural name mustn't be blank");
    }

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
}
