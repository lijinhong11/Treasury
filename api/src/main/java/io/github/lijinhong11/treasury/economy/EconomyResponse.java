package io.github.lijinhong11.treasury.economy;

public record EconomyResponse(double amount, double balance, Result result, String errorMessage) {
    public EconomyResponse(double amount, double balance, Result result) {
        this(amount, balance, result, null);
    }

    public enum Result {
        SUCCESS,
        FAILED,
        UNSUPPORTED
    }

    public boolean operationSuccess() {
        return result == Result.SUCCESS;
    }
}
