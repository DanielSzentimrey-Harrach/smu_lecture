# Spec: Initial Calculator App

## Summary

This is the initial version of a simple desktop calculator application built in Java with Swing. The app must be easy to use, reliable, and consistent with a basic Windows-style calculator experience while staying intentionally small and testable.

The initial version focuses on the core calculator behavior: arithmetic operations, decimal and negative number handling, keyboard and button input, a clear error state for invalid math, and a history panel that tracks the most recent ten calculations.

## User-facing goals

The calculator must:

- Support addition, subtraction, multiplication, and division.
- Accept decimal values and negative values.
- Work through button clicks and keyboard input.
- Display the current value or result clearly in the main display area.
- Show an error message when an invalid operation occurs, especially division by zero.
- Keep a running list of the last 10 calculations in a right-side panel.
- Reset or clear state without losing prior history.

## Functional requirements

### 1. Basic arithmetic

The calculator must perform the four basic arithmetic operators:

- addition
- subtraction
- multiplication
- division

The calculation model should behave like a standard calculator in basic mode: calculations are processed left-to-right without scientific precedence rules.

### 2. Numeric input

The app must support:

- digits 0 through 9
- decimal point
- negative number entry via a sign toggle
- entry values that include decimals and negative numbers

The app must not crash or produce unhandled exceptions when users enter malformed or invalid numeric input. Invalid input should be ignored or rejected gracefully.

### 3. Error handling

Division by zero must result in a clear error state displayed in the main display area, such as:

- "Cannot divide by zero"
- "Error"

The app must never crash or throw an uncaught exception for invalid arithmetic inputs. After an error, the user should be required to clear the state before continuing normal calculation.

### 4. Calculator controls

The calculator must include:

- digits 0–9
- decimal point
- sign toggle (+/-)
- operators: +, -, *, /
- equals
- clear entry (CE)
- clear (C)

The full clear action resets the current calculation state and display, but must not erase the calculation history. The clear entry action resets only the current input.

### 5. Keyboard support

The app must accept keyboard input for the same actions provided by the buttons, including:

- number keys
- decimal key
- arithmetic keys
- Enter or =
- Backspace
- Escape or C

Keyboard handling must use the same logic path as the UI buttons rather than duplicating separate behavior.

### 6. Calculation history

The calculator must show a history panel on the right side of the window listing up to the last 10 calculations.

Each history item must follow the pattern:

- expression = result

Example:

- 12 + 8 = 20

The newest calculation should appear first, and the oldest entries should be discarded once the history exceeds 10 entries.

### 7. Layout and usability

The application must present a simple desktop window with:

- a display area at the top
- a keypad below it
- a right-side history panel

The interface must be usable by mouse and keyboard without additional configuration.

## Non-goals for this initial version

This initial version does not include:

- scientific calculator mode
- programmer mode
- memory buttons such as M+, M-, MR, MC
- unit conversion
- persistent storage of history
- theming or multi-language support
- networking

## Acceptance criteria

1. The app launches as a Swing desktop window and displays a main calculator interface.
2. The user can perform addition, subtraction, multiplication, and division using button input.
3. Decimal numbers and negative numbers work correctly in the current calculation flow.
4. The app evaluates basic calculations left-to-right without operator precedence rules.
5. Division by zero displays a clear error state instead of crashing or throwing an uncaught exception.
6. The user can clear the current entry with CE and reset the calculation state with C.
7. Keyboard input triggers the same logic as the corresponding buttons.
8. The history panel displays up to 10 recent calculations in newest-first order.
9. The oldest history item is removed when a new entry would push the list past 10 items.
10. The app remains simple, readable, and testable, with business logic separated from Swing UI code.

## Why this matters

This initial version delivers the essential calculator experience without unnecessary complexity. It focuses on correctness, simple UI behavior, and maintainable logic so the project can be implemented with strict TDD and remain easy to extend in the future if needed.
