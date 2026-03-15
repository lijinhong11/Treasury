package io.github.lijinhong11.treasury;

import io.github.lijinhong11.treasury.services.ChatServiceRegistry;
import io.github.lijinhong11.treasury.services.EconomyServiceRegistry;
import io.github.lijinhong11.treasury.services.PermissionServiceRegistry;

import java.util.Objects;
import java.util.logging.Logger;

public class Treasury {
    private static final Logger LOGGER = Logger.getLogger("Treasury");

    private static final EconomyServiceRegistry ECONOMY = new EconomyServiceRegistry();
    private static final PermissionServiceRegistry PERMISSION = new PermissionServiceRegistry();
    private static final ChatServiceRegistry CHAT = new ChatServiceRegistry();

    private static TreasuryConfig CONFIG;

    private Treasury() {}

    public static Logger logger() {
        return LOGGER;
    }

    public static EconomyServiceRegistry economyService() {
        return ECONOMY;
    }

    public static PermissionServiceRegistry permissionService() {
        return PERMISSION;
    }

    public static ChatServiceRegistry chatService() {
        return CHAT;
    }

    public static void setConfig(TreasuryConfig config) {
        Objects.requireNonNull(config, "treasury config mustn't be null");

        CONFIG = config;
    }

    public static TreasuryConfig config() {
        return CONFIG;
    }
}
