package com.calculator.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalculationHistoryTest {

    @Test
    void addingCalculation_keepsNewestFirst() {
        CalculationHistory history = new CalculationHistory();

        history.add("2 + 2 = 4");
        history.add("3 + 3 = 6");

        assertEquals("3 + 3 = 6", history.getEntries().get(0));
        assertEquals("2 + 2 = 4", history.getEntries().get(1));
    }

    @Test
    void moreThanTenEntries_keepsOnlyTenNewest() {
        CalculationHistory history = new CalculationHistory();

        for (int i = 1; i <= 11; i++) {
            history.add("calc " + i);
        }

        assertEquals(10, history.getEntries().size());
        assertEquals("calc 11", history.getEntries().get(0));
        assertEquals("calc 2", history.getEntries().get(9));
    }

    @Test
    void emptyHistory_hasNoEntries() {
        CalculationHistory history = new CalculationHistory();

        assertTrue(history.getEntries().isEmpty());
    }
}
