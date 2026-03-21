package io.github.lijinhong11.treasury.services;

import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.permission.PermissionProvider;

import java.util.Objects;

public final class PermissionServiceRegistry implements SingleServiceRegistry<PermissionProvider> {
    private PermissionProvider provider;

    @Override
    public void register(PermissionProvider provider) {
        Objects.requireNonNull(provider, "permission provider mustn't null");

        if (this.provider != null) {
            throw new IllegalStateException("permission provider is already registered");
        }

        if (Treasury.config().isDebug()) {
            Treasury.logger().info("Registered permission provider: " + provider.getName());
        }

        this.provider = provider;
    }

    @Override
    public boolean isRegistered() {
        return provider != null;
    }

    @Override
    public PermissionProvider get() {
        if (provider == null) {
            throw new IllegalStateException("No permission provider registered");
        }

        return provider;
    }
}
