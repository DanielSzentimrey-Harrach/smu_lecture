# Tasks: Initial Calculator App

## Definition of done

The initial calculator app is complete when:

- the arithmetic engine passes all unit tests
- the history logic passes all unit tests
- the UI is wired to the tested logic
- `mvn test` passes without failures
- the app runs and can be smoke-tested in a desktop environment

## Checklist

- [x] Confirm the constitution and feature scope for the initial calculator app
- [x] Write failing tests for the arithmetic engine
- [x] Implement the arithmetic engine and result/error handling
- [x] Write failing tests for the calculation history behavior
- [x] Implement the history manager with a 10-item cap and newest-first ordering
- [x] Wire the Swing UI to the business logic
- [x] Verify the display, keypad, and error state behavior manually
- [x] Run `mvn test` and confirm all tests pass
- [x] Run a final smoke test of the desktop application

## TDD flow

1. Add a failing test for a single arithmetic behavior.
2. Implement only the code required to satisfy that test.
3. Refactor while keeping the tests green.
4. Repeat for the next behavior until the initial calculator is complete.
