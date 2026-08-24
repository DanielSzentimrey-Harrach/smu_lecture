package com.calculator.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CalculatorEngineTest {

    @Test
    void addingTwoPositiveNumbers_returnsSum() {
        CalculationResult result = CalculatorEngine.add(new BigDecimal("2.5"), new BigDecimal("3.25"));

        assertEquals("5.75", result.value().toPlainString());
        assertTrue(result.isSuccess());
    }

    @Test
    void subtractingNumbers_handlesNegativeResults() {
        CalculationResult result = CalculatorEngine.subtract(new BigDecimal("2.5"), new BigDecimal("5.0"));

        assertEquals("-2.5", result.value().toPlainString());
        assertTrue(result.isSuccess());
    }

    @Test
    void multiplyingDecimalNumbers_preservesPrecision() {
        CalculationResult result = CalculatorEngine.multiply(new BigDecimal("0.1"), new BigDecimal("0.2"));

        assertEquals("0.02", result.value().toPlainString());
        assertTrue(result.isSuccess());
    }

    @Test
    void dividingByZero_returnsErrorResult() {
        CalculationResult result = CalculatorEngine.divide(new BigDecimal("10"), BigDecimal.ZERO);

        assertTrue(result.isError());
        assertEquals("Cannot divide by zero", result.errorMessage());
    }

    @Test
    void dividingDecimalNumbers_returnsDecimalResult() {
        CalculationResult result = CalculatorEngine.divide(new BigDecimal("7.5"), new BigDecimal("2.5"));

        assertEquals("3", result.value().toPlainString());
        assertTrue(result.isSuccess());
    }
}
