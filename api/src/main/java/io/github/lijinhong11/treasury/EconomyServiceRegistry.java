package io.github.lijinhong11.treasury;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class EconomyServiceRegistry {
    private final Map<String, EconomyProvider> providers = new ConcurrentHashMap<>();

    private volatile EconomyProvider primary;

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

    public EconomyProvider getPrimary() {
        if (primary == null) {
            throw new IllegalStateException("No economy provider registered");
        }

        return primary;
    }

    public boolean hasPrimary() {
        return primary != null;
    }

    public EconomyProvider getByName(String name) {
        return providers.get(name);
    }

    public Collection<EconomyProvider> getAll() {
        return providers.values();
    }

    public boolean isRegistered(String name) {
        return providers.containsKey(name);
    }
}