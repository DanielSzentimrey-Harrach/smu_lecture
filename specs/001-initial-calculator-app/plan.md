# Plan: Initial Calculator App

## Goal

Implement the first version of the Java Swing calculator as a small, simple application with a testable arithmetic engine and an in-memory history tracker.

## Architecture

The implementation should follow the project constitution and keep the design intentionally minimal.

- `CalculatorApp` starts the Swing UI.
- `CalculatorFrame` assembles the main window, display, keypad, and history panel in a compact initial implementation.
- `CalculatorEngine` contains the pure calculation logic.
- `CalculationResult` is a small immutable record for results and error states.
- `CalculationHistory` tracks the last 10 calculation strings.

This first version intentionally keeps the Swing composition in a single frame to avoid unnecessary UI abstraction. The design remains consistent with the constitution: business logic stays separate from Swing, and additional UI helper classes can be introduced later if the app grows.

## Core technical decisions

- Use `BigDecimal` rather than `double` to avoid floating-point rounding issues in user-facing arithmetic.
- Use a simple left-to-right evaluation model for chained operations, matching standard Windows Calculator behavior.
- Keep the arithmetic and history logic separate from Swing code so it can be unit tested without UI dependencies.
- Keep all UI classes thin and focused on wiring components and rendering outputs.

## Testing approach

Write failing unit tests first for the business logic, then implement the minimal code that satisfies them.

Planned test coverage includes:

- addition, subtraction, multiplication, and division
- decimal arithmetic
- negative numbers
- division by zero
- chained operations without precedence
- history cap of 10 entries
- newest-first ordering
- maximum entry length safety

## Risk and edge cases

- Floating-point precision in decimal operations should be handled with `BigDecimal`.
- Division by zero must produce an error value instead of an exception.
- Long numeric inputs should be limited to a reasonable maximum length to avoid pathological input.
- History should remain in-memory only and reset on app launch.

## Implementation order

1. Define the arithmetic engine and write failing tests.
2. Implement calculation result/error handling.
3. Add the in-memory history class and tests.
4. Wire the Swing UI to the tested logic.
5. Verify the app with `mvn test` and a quick smoke test.
