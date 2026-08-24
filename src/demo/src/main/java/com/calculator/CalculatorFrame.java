package com.calculator;

import com.calculator.engine.CalculationResult;
import com.calculator.engine.CalculatorEngine;
import com.calculator.history.CalculationHistory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorFrame extends JFrame {
    private final JTextField displayField;
    private final DefaultListModel<String> historyModel;
    private final JList<String> historyList;
    private final CalculationHistory calculationHistory = new CalculationHistory();
    private BigDecimal accumulator = BigDecimal.ZERO;
    private BigDecimal pendingValue = null;
    private String pendingOperator = null;
    private boolean isErrorState = false;

    public CalculatorFrame() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        displayField = new JTextField("0");
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        displayField.setPreferredSize(new Dimension(300, 60));

        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setFixedCellHeight(24);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String action = resolveKeyAction(e.getKeyCode(), e.getKeyChar());
                if (action != null) {
                    handleButton(action);
                }
            }
        });
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        JPanel keypad = createKeypadPanel();
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(displayField, BorderLayout.NORTH);
        mainPanel.add(keypad, BorderLayout.CENTER);

        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(new JLabel("History", SwingConstants.CENTER), BorderLayout.NORTH);
        historyPanel.add(new JScrollPane(historyList), BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(mainPanel, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.EAST);
    }

    public static String resolveKeyAction(int keyCode, char keyChar) {
        if (keyChar == '*' || keyChar == '+' || keyChar == '/' || keyChar == '-' || keyChar == '.' || keyChar == '=') {
            return switch (keyChar) {
                case '*' -> "*";
                case '+' -> "+";
                case '/' -> "/";
                case '-' -> "-";
                case '.' -> ".";
                case '=' -> "=";
                default -> null;
            };
        }

        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9) {
            return String.valueOf(keyCode - KeyEvent.VK_0);
        }
        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            return String.valueOf(keyCode - KeyEvent.VK_NUMPAD0);
        }

        switch (keyCode) {
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
                return "+";
            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                return "-";
            case KeyEvent.VK_MULTIPLY:
                return "*";
            case KeyEvent.VK_DIVIDE:
                return "/";
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_EQUALS:
                return "=";
            case KeyEvent.VK_ESCAPE:
                return "C";
            case KeyEvent.VK_BACK_SPACE:
                return "CE";
            case KeyEvent.VK_DECIMAL:
                return ".";
            default:
                return null;
        }
    }

    private JPanel createKeypadPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4, 8, 8));
        String[] labels = {
            "C", "CE", "+/-", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };

        for (String label : labels) {
            if (label.isEmpty()) {
                panel.add(new JLabel());
                continue;
            }
            JButton button = new JButton(label);
            button.addActionListener(e -> handleButton(label));
            panel.add(button);
        }

        return panel;
    }

    private void handleButton(String buttonText) {
        switch (buttonText) {
            case "C" -> {
                resetCalculator();
                displayField.setText("0");
            }
            case "CE" -> {
                accumulator = BigDecimal.ZERO;
                pendingValue = null;
                pendingOperator = null;
                isErrorState = false;
                displayField.setText("0");
            }
            case "+/-" -> {
                if (isErrorState) {
                    return;
                }
                accumulator = accumulator.negate();
                displayField.setText(accumulator.stripTrailingZeros().toPlainString());
            }
            case "/", "*", "+", "-" -> {
                if (isErrorState) {
                    return;
                }
                pendingOperator = buttonText;
                pendingValue = accumulator;
                accumulator = BigDecimal.ZERO;
                displayField.setText("0");
            }
            case "=" -> {
                if (isErrorState || pendingOperator == null || pendingValue == null) {
                    return;
                }
                CalculationResult result = applyOperator(pendingOperator, pendingValue, accumulator);
                if (result.isError()) {
                    isErrorState = true;
                    displayField.setText(result.errorMessage());
                    resetPendingState();
                    return;
                }

                String leftOperandText = formatForDisplay(pendingValue);
                String rightOperandText = formatForDisplay(accumulator);
                String expression = leftOperandText + " " + pendingOperator + " " + rightOperandText + " = " + formatForDisplay(result.value());
                calculationHistory.add(expression);
                refreshHistory();

                accumulator = result.value();
                pendingOperator = null;
                pendingValue = null;
                displayField.setText(formatForDisplay(accumulator));
            }
            case "." -> appendDigit(".");
            default -> {
                if (buttonText.matches("[0-9]")) {
                    appendDigit(buttonText);
                }
            }
        }
    }

    private void appendDigit(String digit) {
        if (isErrorState) {
            resetCalculator();
        }
        String currentText = displayField.getText();
        if ("0".equals(currentText) && !".".equals(digit)) {
            displayField.setText(digit);
        } else if ("0".equals(currentText) && ".".equals(digit)) {
            displayField.setText("0.");
        } else if ("-0".equals(currentText) && !".".equals(digit)) {
            displayField.setText("-" + digit);
        } else {
            displayField.setText(currentText + digit);
        }
        accumulator = new BigDecimal(displayField.getText());
    }

    private String formatForDisplay(BigDecimal value) {
        if (value == null) {
            return "0";
        }
        return new BigDecimal(value.toPlainString()).toPlainString();
    }

    private CalculationResult applyOperator(String operator, BigDecimal left, BigDecimal right) {
        return switch (operator) {
            case "+" -> CalculatorEngine.add(left, right);
            case "-" -> CalculatorEngine.subtract(left, right);
            case "*" -> CalculatorEngine.multiply(left, right);
            case "/" -> CalculatorEngine.divide(left, right);
            default -> CalculationResult.success(right);
        };
    }

    private void refreshHistory() {
        List<String> entries = calculationHistory.getEntries();
        historyModel.clear();
        for (String entry : entries) {
            historyModel.addElement(entry);
        }
    }

    private void resetPendingState() {
        pendingOperator = null;
        pendingValue = null;
    }

    private void resetCalculator() {
        accumulator = BigDecimal.ZERO;
        pendingValue = null;
        pendingOperator = null;
        isErrorState = false;
    }
}
