package com.calculator.engine;

import java.math.BigDecimal;

public record CalculationResult(BigDecimal value, String errorMessage) {
    public boolean isSuccess() {
        return value != null && errorMessage == null;
    }

    public boolean isError() {
        return errorMessage != null && value == null;
    }

    public static CalculationResult success(BigDecimal value) {
        return new CalculationResult(value, null);
    }

    public static CalculationResult error(String errorMessage) {
        return new CalculationResult(null, errorMessage);
    }
}
