package io.github.lijinhong11.treasury;

import java.util.Objects;
import java.util.logging.Logger;

public class Treasury {
    private static final Logger LOGGER = Logger.getLogger("Treasury");

    private static final EconomyServiceRegistry ECONOMY = new EconomyServiceRegistry();

    private static TreasuryConfig CONFIG;

    private Treasury() {}

    public static Logger logger() {
        return LOGGER;
    }

    public static EconomyServiceRegistry economy() {
        return ECONOMY;
    }

    public static void setConfig(TreasuryConfig config) {
        Objects.requireNonNull(config, "treasury config mustn't be null");

        CONFIG = config;
    }

    public static TreasuryConfig config() {
        return CONFIG;
    }
}
