package io.github.lijinhong11.treasury;

public class TreasuryConfigImpl implements TreasuryConfig {
    private String primaryEconomy = "";
    private boolean debug = false;

    public TreasuryConfigImpl() {
    }

    public TreasuryConfigImpl(String primaryEconomy, boolean debug) {
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
