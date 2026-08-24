package com.calculator.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculatorEngine {
    private static final int SCALE = 12;

    private CalculatorEngine() {
    }

    public static CalculationResult add(BigDecimal left, BigDecimal right) {
        return CalculationResult.success(normalize(left.add(right)));
    }

    public static CalculationResult subtract(BigDecimal left, BigDecimal right) {
        return CalculationResult.success(normalize(left.subtract(right)));
    }

    public static CalculationResult multiply(BigDecimal left, BigDecimal right) {
        return CalculationResult.success(normalize(left.multiply(right)));
    }

    public static CalculationResult divide(BigDecimal left, BigDecimal right) {
        if (right.compareTo(BigDecimal.ZERO) == 0) {
            return CalculationResult.error("Cannot divide by zero");
        }

        return CalculationResult.success(normalize(left.divide(right, SCALE, RoundingMode.HALF_UP)));
    }

    private static BigDecimal normalize(BigDecimal value) {
        return value.stripTrailingZeros();
    }
}
