package io.github.lijinhong11.treasury.services;

import io.github.lijinhong11.treasury.Treasury;
import io.github.lijinhong11.treasury.chat.ChatProvider;

import java.util.Objects;

public final class ChatServiceRegistry implements SingleServiceRegistry<ChatProvider> {
    private ChatProvider provider;

    @Override
    public void register(ChatProvider provider) {
        Objects.requireNonNull(provider, "chat provider mustn't null");

        if (this.provider != null) {
            throw new IllegalStateException("chat provider is already registered");
        }

        if (Treasury.config().isDebug()) {
            Treasury.logger().info("Registered chat provider: " + provider.getName());
        }

        this.provider = provider;
    }

    @Override
    public boolean isRegistered() {
        return provider != null;
    }

    @Override
    public ChatProvider get() {
        if (provider == null) {
            throw new IllegalStateException("No chat provider registered");
        }

        return provider;
    }
}
