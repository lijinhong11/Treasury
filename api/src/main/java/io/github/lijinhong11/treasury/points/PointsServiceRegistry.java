package io.github.lijinhong11.treasury.points;

import io.github.lijinhong11.treasury.Treasury;

import java.util.Objects;

public final class PointsServiceRegistry {
    private PointsProvider provider;
    
    public void register(PointsProvider provider) {
        Objects.requireNonNull(provider, "points provider mustn't null");

        if (this.provider != null) {
            throw new IllegalStateException("points provider is already registered");
        }

        if (Treasury.config().isDebug()) {
            Treasury.logger().info("Registered points provider: " + provider.getName());
        }

        this.provider = provider;
    }
    
    public boolean isRegistered() {
        return provider != null;
    }
    
    public PointsProvider get() {
        if (provider == null) {
            throw new IllegalStateException("No points provider registered");
        }

        return provider;
    }
}