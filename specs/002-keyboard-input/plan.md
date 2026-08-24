# Plan: Keyboard Input Support

## Goal

Add keyboard interaction to the calculator so the user can perform calculations using standard keyboard keys without relying on mouse clicks.

## Architecture

This feature is a UI-layer enhancement built on top of the already-tested calculation engine and history logic.

- `CalculatorFrame` remains the main entry point for input handling.
- Keyboard listeners are added to the window or focusable container.
- The key handler delegates to the same logic currently used by the calculator buttons.
- Arithmetic, validation, and history behavior continue to be handled by the existing engine and history classes.

## Implementation approach

- Add a key listener or key binding to the main calculator window.
- Map supported keys to the same operations used by the button handlers.
- Route all keyboard actions through `handleButton(...)` or a shared internal method to avoid duplicated logic.
- Keep invalid key presses ignored rather than throwing exceptions.
- Preserve the existing error and history behavior.

## Edge cases

- `.` should only be inserted once per numeric entry if the app currently enforces that rule.
- `Enter` and `=` should both perform the same evaluate action.
- `Shift` combinations that produce arithmetic symbols, such as `*` from `Shift + 8` and `+` from `Shift + =`, must be treated the same as the direct operator keys.
- `C` and `Escape` should clear the current calculation state without erasing history.
- Unsupported keys should be ignored cleanly.
- The app should not use a separate keyboard-only implementation that drifts from the button-based behavior.

## Testing approach

Write failing tests for UI-aware keyboard mapping before implementation, if the app is structured to support testable input handling. At minimum, validate that the keyboard handler delegates to the same operations as the button path and does not change behavior for invalid input.

## Definition of done

The feature is complete when:

- standard calculator keys work for both digit and operator entry
- Shift-accessible arithmetic keys such as `*` and `+` work the same as their button equivalents
- evaluation works with Enter or =
- clear behavior matches the button UI
- keyboard actions produce the same results and history entries as mouse-based input
- the app still passes the existing unit tests and behaves correctly in a smoke test
