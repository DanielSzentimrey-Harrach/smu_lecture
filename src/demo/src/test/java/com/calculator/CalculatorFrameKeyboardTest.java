package com.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;
import org.junit.jupiter.api.Test;

class CalculatorFrameKeyboardTest {

    @Test
    void keyMapping_usesExpectedCalculatorActions() {
        assertEquals("7", CalculatorFrame.resolveKeyAction(KeyEvent.VK_7, '7'));
        assertEquals("+", CalculatorFrame.resolveKeyAction(KeyEvent.VK_PLUS, '+'));
        assertEquals("*", CalculatorFrame.resolveKeyAction(KeyEvent.VK_8, '*'));
        assertEquals("=", CalculatorFrame.resolveKeyAction(KeyEvent.VK_EQUALS, '='));
        assertEquals("C", CalculatorFrame.resolveKeyAction(KeyEvent.VK_ESCAPE, '\u001B'));
        assertEquals("CE", CalculatorFrame.resolveKeyAction(KeyEvent.VK_BACK_SPACE, '\b'));
    }

    @Test
    void shiftAccessibleOperators_areRecognizedAsArithmeticKeys() {
        assertEquals("*", CalculatorFrame.resolveKeyAction(KeyEvent.VK_8, '*'));
        assertEquals("+", CalculatorFrame.resolveKeyAction(KeyEvent.VK_EQUALS, '+'));
    }
}
