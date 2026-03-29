package io.github.lijinhong11.treasury;

import io.github.lijinhong11.treasury.economy.EconomyServiceRegistry;
import io.github.lijinhong11.treasury.points.PointsServiceRegistry;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The main class of Treasury API
 */
public class Treasury {
    private static final Logger LOGGER = Logger.getLogger("Treasury");

    private static final EconomyServiceRegistry ECONOMY = new EconomyServiceRegistry();
    private static final PointsServiceRegistry POINTS = new PointsServiceRegistry();

    private static TreasuryConfig CONFIG;

    private Treasury() {}

    /**
     * Gets the logger
     * @return the logger
     */
    public static Logger logger() {
        return LOGGER;
    }

    /**
     * Gets the economy service
     * @return the economy service
     */
    public static EconomyServiceRegistry economy() {
        return ECONOMY;
    }

    /**
     * Gets the points service
     * @return the points service
     */
    public static PointsServiceRegistry points() {
        return POINTS;
    }

    /**
     * Sets the config
     * <br>
     * <b>NOTE: THIS IS AN INTERNAL API METHOD</b>
     * @param config the config
     */
    public static void setConfig(TreasuryConfig config) {
        Objects.requireNonNull(config, "treasury config mustn't be null");

        CONFIG = config;
    }

    /**
     * Gets the config
     * @return the config
     */
    public static TreasuryConfig config() {
        return CONFIG;
    }
}
