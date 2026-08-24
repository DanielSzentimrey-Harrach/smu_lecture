# Spec: Keyboard Input Support

## Summary

This feature adds keyboard support to the calculator so the user can operate the app without relying only on mouse clicks. The same calculator logic used by the UI buttons must be triggered by keyboard events, preserving consistent behavior and reducing duplicated logic.

The feature is intended to make the calculator feel like a standard desktop calculator while keeping the implementation simple, reliable, and testable.

## User-facing goals

The calculator must support the following keyboard interactions:

- number keys for digits 0–9
- decimal key for the decimal point
- arithmetic keys for +, -, *, and /
- Shift-accessible arithmetic keys, including `*` from `Shift + 8` and `+` from `Shift + =`, when available on the keyboard layout
- Enter or = to evaluate the current calculation
- Backspace to delete the last entered character or clear the current input in the usual calculator way
- Escape or C to clear the current state

The user should be able to complete a full calculation using only the keyboard without needing to click any buttons.

## Functional requirements

### 1. Input mapping

Each supported keyboard key must map to the behavior of the equivalent button on the calculator UI.

Examples:

- `0`–`9` map to digit buttons
- `.` maps to the decimal button
- `+`, `-`, `*`, `/` map to the operator buttons
- Shift-accessible operator characters, such as `*` from `Shift + 8` and `+` from `Shift + =`, also map to the same operator buttons
- `Enter` and `=` map to the equals button
- `Backspace` maps to clear-entry or backspace behavior
- `Escape` and `C` map to the clear action

### 2. Shared logic path

Keyboard input must not use a separate branch of logic that behaves differently from the mouse-driven path. The UI should delegate to the same calculation logic and state updates used by the buttons.

This is important so that:

- arithmetic results stay consistent
- error handling stays consistent
- history behavior stays consistent
- the app remains easier to maintain and test

### 3. Validation and error handling

Keyboard input must ignore invalid or unsupported keys instead of throwing exceptions or corrupting the calculator state.

If the user presses a key that would trigger an invalid operation, the calculator must behave the same way as the button-based version, including showing a clear error state for division by zero.

### 4. Clear and reset behavior

Keyboard-triggered clear behavior must match the UI button behavior:

- Escape or C resets the calculator state and display
- Backspace or CE clears the current input appropriately, depending on the app’s existing semantics

The calculation history must remain intact when the calculator is cleared unless the UI explicitly defines a separate behavior.

### 5. Usability

Keyboard support must work while the calculator window is focused and should not require the user to click inside the display before typing.

The app should feel natural to use with both mouse and keyboard, with no duplicated functionality.

## Non-goals

This feature does not add:

- a separate keyboard-only mode
- scientific calculator support
- custom key bindings beyond the standard calculator keys listed above
- persistence of history or settings
- new dependencies or alternative UI frameworks

## Acceptance criteria

1. The user can enter numbers and operators using the keyboard.
2. Shift-accessible arithmetic symbols such as `*` from `Shift + 8` and `+` from `Shift + =` are accepted as operator input.
3. Pressing `Enter` or `=` evaluates the current calculation.
4. Pressing `Escape` or `C` clears the current state the same way as the clear button.
5. Unsupported keys are ignored without crashing or changing state unexpectedly.
6. Keyboard input produces the same calculation results and error handling as the button-based UI.
7. Keyboard actions and button actions share the same logic path.
8. The history panel updates correctly for keyboard-triggered calculations.

## Why this matters

Keyboard support is a core usability requirement for a desktop calculator. It reduces friction, makes the app more accessible, and keeps the user experience consistent with standard calculator behavior without overcomplicating the design.
