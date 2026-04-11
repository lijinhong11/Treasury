package io.github.lijinhong11.treasury.economy;

import io.github.lijinhong11.treasury.Treasury;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class EconomyServiceRegistry {
    private final Map<String, EconomyProvider> providers = new ConcurrentHashMap<>();

    private volatile EconomyProvider primary;

    /**
     * Register an economy provider
     * @param provider the economy provider
     */
    public void register(EconomyProvider provider) {
        Objects.requireNonNull(provider, "economy provider mustn't be null");

        if (providers.containsKey(provider.getName())) {
            throw new IllegalStateException("The economy provider is already registered: " + provider.getName());
        }

        providers.put(provider.getName(), provider);

        if (Treasury.config().isDebug()) {
            Treasury.logger().info("Registered economy provider: " + provider.getName());
        }

        String configured = Treasury.config().getPrimaryEconomy();

        if (configured != null && !configured.isBlank()) {
            if (configured.equals(provider.getName())) {
                primary = provider;

                if (Treasury.config().isDebug()) {
                    Treasury.logger().info("Set primary economy provider (config): " + provider.getName());
                }
            }
            return;
        }

        if (primary == null) {
            primary = provider;

            if (Treasury.config().isDebug()) {
                Treasury.logger().info("Set primary economy provider (auto): " + provider.getName());
            }
        }
    }

    /**
     * Set an economy provider as the primary provider.<br>
     * <b>NOTE:
     * Except for special situations, it is generally not recommended to use!</b>
     * @param name the economy provider's name
     */
    public void setPrimary(String name) {
        if (name == null || name.isBlank()) {
            return;
        }

        EconomyProvider provider = providers.get(name);

        if (provider == null) {
            throw new IllegalArgumentException("Unknown economy provider: " + name);
        }

        if (Treasury.config().isDebug()) {
            Treasury.logger().info("Set primary provider (force): " + provider.getName());
        }

        this.primary = provider;
    }

    /**
     * Gets the primary economy provider
     * @return the primary economy provider
     */
    public EconomyProvider getPrimary() {
        if (primary == null) {
            throw new IllegalStateException("No economy provider registered");
        }

        return primary;
    }

    /**
     * Check the primary economy provider has been set
     * @return whether the primary economy provider has been set
     */
    public boolean hasPrimary() {
        return primary != null;
    }

    /**
     * Gets the economy provider by its name
     * @param name the economy provider's name
     * @return the economy provider or null if not found
     */
    public EconomyProvider getByName(String name) {
        return providers.get(name);
    }

    /**
     * Gets all the economy providers
     * @return all the economy providers
     */
    public List<EconomyProvider> getAll() {
        return List.copyOf(providers.values());
    }

    /**
     * Check an economy provider with that name is existed
     * @param name the economy provider's name
     * @return whether an economy provider with that name is existed
     */
    public boolean isRegistered(String name) {
        return providers.containsKey(name);
    }
}