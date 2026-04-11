package io.github.lijinhong11.treasury.economy;

import java.math.BigDecimal;

/**
 * Represents the result of an economy operation.
 *
 * @param amount amount involved in the operation
 * @param balance resulting balance after the operation
 * @param result operation result
 * @param errorMessage error message when the operation fails (nullable)
 */
public record EconomyResponse(BigDecimal amount, BigDecimal balance, Result result, String errorMessage) {
    /**
     * Creates a response without an error message.
     *
     * @param amount amount involved in the operation
     * @param balance resulting balance after the operation
     * @param result operation result
     */
    public EconomyResponse(BigDecimal amount, BigDecimal balance, Result result) {
        this(amount, balance, result, null);
    }

    /**
     * Available economy operation outcomes.
     */
    public enum Result {
        SUCCESS,
        FAILED,
        UNSUPPORTED
    }

    /**
     * Checks whether the operation completed successfully.
     *
     * @return true if the result is {@link Result#SUCCESS}
     */
    public boolean operationSuccess() {
        return result == Result.SUCCESS;
    }
}
