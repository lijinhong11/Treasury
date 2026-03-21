package io.github.lijinhong11.treasury;

/**
 * This is a global configuration for Treasury mod.
 */
public interface TreasuryConfig {
    /**
     * Gets the configured primary economy provider name.
     * <br>
     * If it is null or blank, Treasury will use the first available provider.
     *
     * @return provider name or empty string
     */
    String getPrimaryEconomy();

    /**
     * Whether debug logging is enabled.
     *
     * @return true if debug logging is enabled
     */
    boolean isDebug();
}
