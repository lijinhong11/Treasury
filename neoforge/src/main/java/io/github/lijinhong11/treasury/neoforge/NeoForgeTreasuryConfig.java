package io.github.lijinhong11.treasury.neoforge;

import io.github.lijinhong11.treasury.TreasuryConfig;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class NeoForgeTreasuryConfig implements TreasuryConfig {
    private String primaryEconomy = "";
    private boolean debug = false;

    public NeoForgeTreasuryConfig() {
    }

    public NeoForgeTreasuryConfig(String primaryEconomy, boolean debug) {
        this.primaryEconomy = primaryEconomy;
        this.debug = debug;
    }

    public String getPrimaryEconomy() {
        return primaryEconomy;
    }

    public void setPrimaryEconomy(String primaryEconomy) {
        this.primaryEconomy = primaryEconomy;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
