# Project Constitution: Java Swing Calculator

**Status:** Ratified
**Applies to:** All contributors and AI coding agents (including GitHub Copilot) working on this repository.

This document is the single source of truth for how this project is built. It takes precedence over
convenience, speed, or any suggestion (human or AI-generated) that conflicts with it. If a Copilot
suggestion, PR, or design idea violates this constitution, it must be rejected or the constitution must
be explicitly amended first — never silently overridden.

---

## 1. Project Overview

A simple, desktop, visual calculator application supporting the four basic arithmetic operations:
addition, subtraction, multiplication, and division.

The application must:
- Provide a graphical, mouse- and keyboard-operable calculator interface.
- Display a running **history of the last 10 calculations** in a panel on the right-hand side of the
  window, similar in spirit to the Windows built-in Calculator's history pane.
- Support **decimal numbers and negative numbers**.
- Handle division by zero gracefully by showing an error state in the display (e.g. `"Cannot divide by zero"`
  or `"Error"`), matching the behavior of Windows Calculator — never crash, never throw an uncaught exception.

### 1.1 Non-Goals (explicitly out of scope)

To keep the project simple, the following are **not** part of this project unless the constitution is
amended:
- Scientific/programmer/graphing calculator modes.
- Memory functions (M+, M-, MR, MC).
- Unit conversion.
- Persisting history or settings across application restarts (history is **in-memory only** and resets
  every time the app is launched).
- Theming, skinning, or multi-language support.
- Networking of any kind.

---

## 2. Spec-Driven Development (SDD)

This project is built using **Spec-Driven Development**. A specification, not a chat prompt or an ad hoc
instruction, is the primary artifact that defines *what* to build. Code — including AI-generated code — is
an implementation of a spec, not a substitute for one. If there is ever a conflict between what a spec
says and what code does, the spec wins, and the code must be fixed (or the spec must be explicitly amended
first).

### 2.1 Where Specs Live

All specs are version-controlled, plain-Markdown files stored in the project tree under `specs/`, alongside
the code they describe:

```
specs/
├── 001-arithmetic-engine/
│   ├── spec.md          # WHAT and WHY: user-facing requirements, acceptance criteria
│   ├── plan.md          # HOW: technical approach, affected classes, package layout
│   └── tasks.md         # Ordered, checkable list of TDD tasks (red/green/refactor steps)
├── 002-calculation-history/
│   ├── spec.md
│   ├── plan.md
│   └── tasks.md
├── 003-keypad-and-display-ui/
│   ├── spec.md
│   ├── plan.md
│   └── tasks.md
└── 004-history-panel-ui/
    ├── spec.md
    ├── plan.md
    └── tasks.md
```

- Each feature/slice of work gets its own numbered folder under `specs/` (`NNN-short-kebab-name/`), numbered
  in the order the feature was specified, not necessarily the order it's built.
- `spec.md` describes **what** the feature must do and why, in plain language, plus explicit, testable
  acceptance criteria. It must not describe class names, method signatures, or implementation detail.
- `plan.md` describes **how** the feature will be implemented within the constraints of this constitution
  (which classes/packages from Section 5 are touched, what new files if any are needed, any edge cases
  worth calling out). It must remain consistent with Section 5 (Architecture) — it does not get to invent
  new layers or abstractions on its own authority.
- `tasks.md` breaks the plan into a small, ordered checklist of TDD steps (e.g. "Write failing test for
  divide-by-zero → Implement `CalculatorEngine.divide` → Refactor"), so both a human and an AI agent can
  track exactly what's done and what's next.
- This constitution (`CONSTITUTION.md`) sits above all specs at the project root. Specs must never
  contradict it; if a spec seems to require something the constitution forbids (a new dependency, an
  unnecessary abstraction, persistence, etc.), the constitution must be amended first (Section 13), and
  only then should the spec be written or updated.

### 2.2 How Specs Are Used

1. **Nothing is coded without a spec.** Before writing or requesting any implementation, a `spec.md` for
   that piece of work must exist and be reviewed. If no spec exists yet for the requested feature, the
   first step is to write one, not to jump to code.
2. **Specs drive the TDD cycle, not the other way around.** The acceptance criteria in `spec.md` are the
   source for the failing tests written in Section 5's Red step. The steps in `tasks.md` should map
   directly onto individual red/green/refactor cycles.
3. **Specs are kept up to date.** If, during implementation, the real behavior needs to diverge from what
   `spec.md` says (an edge case was missed, a requirement was ambiguous), stop and update the spec first,
   then continue. Do not let the spec silently drift out of sync with the code.
4. **Specs are reviewed like code.** Treat spec files as first-class, reviewable artifacts (they live in
   Git, get PR review, and can be diffed) — not throwaway notes.

### 2.3 Instructions for AI Agents (GitHub Copilot and others)

Any AI agent operating in this repository — including GitHub Copilot in agent mode — must:

1. **Read before writing.** Before generating any code for a feature, locate and read the relevant
   `specs/NNN-*/spec.md` and `plan.md`. If neither exists for the requested feature, generate them first
   (spec.md, then plan.md, then tasks.md), get them confirmed by the user, and only then proceed to code.
2. **Never invent requirements.** Do not add behavior, options, or UI elements that are not present in
   `spec.md`. If a request seems to require something outside the current spec, flag it and propose a spec
   update rather than silently expanding scope in code.
3. **Follow `tasks.md` in order**, and check off tasks as they are completed, keeping the file an accurate,
   living record of progress.
4. **Enforce the constitution over the spec** if the two ever conflict — surface the conflict to the user
   explicitly rather than picking one silently.
5. **Keep specs and code in the same commit/PR** where practical, so the history always shows the spec
   change alongside the implementation it drove.

---

## 3. Guiding Principles

1. **Simplicity first.** This is a small, single-purpose desktop app. Prefer the simplest design that
   satisfies the requirements. Do not introduce layers of abstraction (interfaces, factories, dependency
   injection frameworks, design patterns) unless there are at least two concrete, current implementations
   that need them. YAGNI ("You Aren't Gonna Need It") is a hard rule here, not a suggestion.
2. **Readable over clever.** Code should be understandable by a junior developer without additional
   explanation. Favor explicit, descriptive names over terse or "smart" code.
3. **Test-Driven Development is mandatory, not optional.** See Section 5. No production code is written
   without a preceding failing test, except for the tiny amount of UI wiring that cannot be reasonably
   unit tested (see Section 5.4).
4. **Separation of concerns, kept minimal.** Arithmetic/calculation logic must be fully decoupled from
   Swing UI code so it can be unit tested without spinning up any UI components. Beyond this one
   separation, do not over-architect.
5. **Small, working increments.** Every commit should leave the project in a state where it compiles, all
   tests pass, and the app runs.
6. **No unnecessary dependencies.** Use only the Java standard library and JUnit 5 (see Section 7). Do not
   add third-party libraries (logging frameworks, utility belts like Guava/Apache Commons, JSON libraries,
   etc.) without an explicit, justified amendment to this constitution.

---

## 4. Technology Stack

| Concern            | Choice                                                  |
|---------------------|----------------------------------------------------------|
| Language            | Java 25                                                  |
| UI Toolkit          | Java Swing (`javax.swing`, `java.awt`) — no third-party UI libraries |
| Build tool          | Maven                                                    |
| Test framework      | JUnit 5 (JUnit Jupiter)                                  |
| Assertions          | JUnit 5's built-in `Assertions`, or AssertJ if readability genuinely benefits (optional, discuss before adding) |
| Version control     | Git / GitHub                                             |
| IDE / tooling       | Visual Studio Code + GitHub Copilot                      |
| Packaging           | Executable JAR via `maven-shade-plugin` or `maven-assembly-plugin` |

### 4.1 Java Version Notes

The project targets **Java 25**. Because this is a recent release, contributors and AI agents must:
- Set `<maven.compiler.release>25</maven.compiler.release>` in `pom.xml` rather than separate source/target
  properties.
- Not assume the availability of preview features unless explicitly enabled and documented — do not use
  `--enable-preview` features in production code without an amendment to this constitution.
- Verify locally that a Java 25 JDK is installed and `JAVA_HOME` points to it before building.

---

## 5. Development Process: Strict Test-Driven Development (TDD)

TDD is not a suggestion in this project — it is enforced as the workflow for **all logic code**
(calculation engine, history management, input parsing/validation). The cycle is:

### 5.1 The Red-Green-Refactor Cycle

1. **RED** — Write a unit test that describes one small piece of desired behavior. Run it and confirm it
   **fails** (and fails for the right reason — a missing/incorrect implementation, not a typo or compile
   error).
2. **GREEN** — Write the *minimum* production code necessary to make that test pass. Do not add
   functionality not demanded by a test.
3. **REFACTOR** — With the safety net of passing tests, clean up the code (naming, duplication,
   readability) without changing behavior. Re-run all tests after every refactor to confirm they still
   pass.
4. Repeat for the next smallest piece of behavior.

**GitHub Copilot / AI agent instruction:** When asked to implement a feature or fix a bug, always propose
or write the failing test(s) first, show that they fail, and only then write the implementation. Never
generate production code and tests in the same step without first demonstrating the test fails against
the old (or absent) implementation.

### 5.2 What Must Be Tested

All of the following must be developed test-first, with unit tests living in `src/test/java`, mirroring
the package structure of `src/main/java`:

- Arithmetic operations (add, subtract, multiply, divide), including:
  - Positive and negative operands.
  - Decimal operands and results (including floating-point precision edge cases).
  - Division by zero.
  - Chained operations (e.g. `5 + 3 * 2 =` behavior, consistent with a simple left-to-right, non-scientific
    calculator — no operator precedence, matching Windows Calculator's standard mode).
- Input parsing/validation (e.g. preventing multiple decimal points, leading zeros handling, negative
  number entry via a sign-toggle button).
- History management logic:
  - Adding a new calculation to history.
  - Enforcing the maximum of 10 entries (oldest entry is discarded once the 11th is added).
  - Correct ordering (most recent calculation should be clearly identifiable — decide and document display
    order: newest-first is recommended, matching Windows Calculator).
- Error state handling (e.g. divide-by-zero produces the correct error value/message from the calculation
  engine, independent of how the UI renders it).

### 5.3 Test Quality Rules

- One logical assertion/behavior per test where practical. Test names must describe the behavior being
  verified, e.g. `dividingByZero_returnsErrorResult()`, not `test1()`.
- Tests must be independent and runnable in any order — no shared mutable state between tests.
- No test should depend on Swing components, threads, or the AWT Event Dispatch Thread. If a class needs
  Swing to be tested, it belongs in the UI layer and is out of scope for unit testing (see 5.4).
- Prefer plain JUnit 5 without mocking frameworks. This project's logic is small and pure enough that
  mocks should not be necessary; if a need for mocking arises, treat it as a signal the design has become
  too complex and simplify instead.

### 5.4 The One Exception: UI Wiring

Swing UI code that only wires components together (creating `JButton`s, attaching `ActionListener`s that
delegate to already-tested logic classes, laying out panels) is difficult and low-value to unit test. This
thin layer is exempt from strict TDD, **but**:
- It must contain **no business logic** — no arithmetic, no history trimming, no validation. It only
  collects input and delegates to tested classes, then renders their output.
- It must be kept as small and "dumb" as possible specifically so this exemption stays narrow.
- Any logic that *can* be extracted and tested, must be extracted and tested.

### 5.5 Definition of Done

A feature or bug fix is only "done" when:
1. Failing test(s) were written first and shown to fail.
2. Implementation makes all tests pass.
3. `mvn test` runs clean with zero failures and zero errors.
4. `mvn verify` / `mvn package` builds successfully.
5. The app was manually smoke-tested by running it (for UI-affecting changes).
6. No compiler warnings were introduced without justification.

---

## 6. Architecture

Keep this project to **three simple layers**, with no interfaces or abstraction beyond what's listed. Do
not add a "framework," MVC library, or dependency injection container.

```
src/main/java/com/calculator/
├── CalculatorApp.java          # main() entry point, launches the UI
├── engine/
│   ├── CalculatorEngine.java   # Pure arithmetic logic: add, subtract, multiply, divide
│   └── CalculationResult.java  # Simple value object/record representing a result or error state
├── history/
│   └── CalculationHistory.java # Manages the in-memory list of the last 10 calculations
└── ui/
    ├── CalculatorFrame.java    # Main JFrame: assembles display, keypad, and history panel
    ├── DisplayPanel.java       # The numeric display at the top
    ├── KeypadPanel.java        # The digit/operator/equals/clear buttons
    └── HistoryPanel.java       # The right-hand history list (e.g. backed by a JList)
```

```
src/test/java/com/calculator/
├── engine/
│   └── CalculatorEngineTest.java
└── history/
    └── CalculationHistoryTest.java
```

```
specs/                             # See Section 2 (Spec-Driven Development)
├── 001-arithmetic-engine/
├── 002-calculation-history/
├── 003-keypad-and-display-ui/
└── 004-history-panel-ui/
```

### 6.1 Design Rules

- `engine` and `history` packages must have **zero imports** from `javax.swing` or `java.awt`. This is
  what makes them unit-testable and keeps the "simple, no abstraction" principle honest — the boundary
  exists because Swing is genuinely untestable in a headless CI environment, not for its own sake.
- Use a `record` (e.g. `CalculationResult`) for simple immutable data carriers instead of hand-written
  getters/setters/equals/hashCode boilerplate — Java 25 records keep this idiomatic and simple.
- `CalculationHistory` should be a plain class wrapping a `LinkedList` or `ArrayDeque`, capped at 10
  entries. No persistence, no database, no file I/O.
- Prefer `BigDecimal` over `double` for arithmetic to avoid floating-point rounding surprises in a
  user-facing calculator (e.g. `0.1 + 0.2` should not display as `0.30000000000000004`). This must be
  covered by tests.
- The UI layer talks to `CalculatorEngine` and `CalculationHistory` directly. Do not introduce a
  ViewModel/Presenter/Controller abstraction layer for an app this size — plain method calls from
  `ActionListener`s are sufficient and preferred.

---

## 7. Testing & Tooling Setup

- **JUnit 5** (`junit-jupiter`) as a `test`-scoped Maven dependency.
- Run tests with `mvn test`. This must be run (and pass) before every commit.
- Use Maven's `maven-surefire-plugin` (default with `mvn test`) — no additional test runners.
- No code coverage threshold is mandated numerically, but the goal is that `engine` and `history` packages
  are effectively fully covered, since they contain 100% of the business logic.
- Keep the `pom.xml` minimal: Maven Compiler Plugin (targeting Java 25), Surefire Plugin, JUnit 5
  dependency, and a packaging plugin (Shade or Assembly) for producing a runnable JAR. Resist adding
  plugins "for later."

---

## 8. Security Considerations

Even for a simple offline desktop calculator, apply baseline good practice:

1. **No dynamic code evaluation.** Never implement expression parsing via `eval`-like mechanisms, scripting
   engines (e.g. Nashorn/GraalJS), or reflection-based dynamic invocation to compute results. Arithmetic
   must be performed by explicit, reviewable Java code in `CalculatorEngine`.
2. **Robust input validation.** All user input (typed via keyboard or entered via buttons) must be
   validated before being parsed into numbers. Reject or ignore malformed input (e.g. multiple decimal
   points, non-numeric keyboard characters) rather than passing it to `BigDecimal`/`Double` parsing and
   letting an exception propagate to the UI.
3. **No uncaught exceptions reach the user as a crash.** Any error condition (divide by zero, invalid
   input, numeric overflow) must be caught at the appropriate layer and converted into a user-visible
   error state in the display, never an unhandled stack trace or an app freeze/crash.
4. **No external network or file-system access.** The app must not read from or write to the file system
   (beyond what the JVM/OS does implicitly) and must not open any network connections. This eliminates
   entire classes of risk (path traversal, unvalidated downloads, data exfiltration) by design.
5. **Dependency hygiene.** Because dependencies are intentionally minimal (JUnit 5 for tests only, no
   runtime third-party libraries), the attack surface from vulnerable dependencies is minimized by design.
   If a dependency is ever added, it must be from Maven Central, pinned to an explicit version, and
   justified in a PR description.
6. **No sensitive data handling.** The app processes only numeric calculator input; it must never log,
   store, or transmit any data beyond the in-memory history required for the feature.
7. **Denial-of-service via input.** Guard against pathological input (e.g. an extremely long digit string)
   causing `BigDecimal` operations to hang or consume excessive memory — cap the maximum length of a
   single number entry (e.g. a reasonable, documented limit such as 15–20 digits) with a test asserting
   this limit is enforced.

---

## 9. UI/UX Specification

- **Window layout:** A single, fixed- or reasonably-resizable `JFrame` divided into:
  - **Left/main area:** Display (top) showing current input/result, and a keypad below it (digits 0–9,
    decimal point, +/- sign toggle, the four operators, equals, and clear/clear-entry).
  - **Right-hand panel:** Scrollable list showing up to the last 10 calculations, each formatted as
    `expression = result` (e.g. `12 + 8 = 20`), newest entry at the top.
- **Keyboard support:** Number keys, `.`, `+`, `-`, `*`, `/`, `Enter`/`=`, `Backspace`, and `Escape`/`C`
  must all be wired to the same logic as their corresponding buttons — no separate/duplicated logic path.
- **Clear behavior:** Provide both a Clear Entry (`CE`, clears current input only) and a full Clear (`C`,
  resets calculator state and current input, but does **not** erase history).
- **Error display:** On division by zero or another invalid operation, show a clear, plain-language message
  in the display area (e.g. `Cannot divide by zero`) and require the user to clear before continuing, same
  as Windows Calculator.

---

## 10. Coding Standards

- Follow standard Java naming conventions (`UpperCamelCase` for classes, `lowerCamelCase` for methods and
  variables, `UPPER_SNAKE_CASE` for constants).
- Package-private/private visibility by default; only make members `public` when they need to be accessed
  from outside their package.
- No wildcard imports.
- Keep methods short and focused (a good target: under ~20–30 lines). If a method grows beyond that, it's
  a signal to extract a well-named private method — not to add a new abstraction layer.
- Prefer immutability: `final` fields and local variables where practical, records for data carriers.
- Write Javadoc only where behavior isn't obvious from the method/class name — do not pad the codebase
  with redundant comments restating the code.

---

## 11. Git & Commit Practices

- Commit early and often, in small, working increments (see Principle 5 in Section 3).
- Each commit message should briefly state intent, e.g. `Add failing test for BigDecimal division`,
  `Implement CalculatorEngine.divide to pass tests`, `Refactor: extract input validation`.
- The TDD cycle should generally be visible in commit history: a "red" test commit can be combined with
  its "green" implementation commit, but do not squash weeks of work into a single opaque commit.
- Do not commit generated build output (`target/`) — ensure `.gitignore` excludes Maven build artifacts,
  `.class` files, and IDE-specific folders (e.g. `.vscode/` settings that are personal, not shared).

---

## 12. How to Use This Constitution with GitHub Copilot in VS Code

- Keep this file at the project root as `CONSTITUTION.md` (or reference it from `.github/copilot-instructions.md`
  so Copilot Chat/agent mode automatically loads it as project-wide custom instructions).
- Also reference the `specs/` folder from `.github/copilot-instructions.md`, so Copilot knows to check it
  for the relevant `spec.md`/`plan.md`/`tasks.md` before generating code for any feature (see Section 2).
- When prompting Copilot for a new feature, explicitly ask it to: "Check `specs/` for an existing spec on
  this feature; if none exists, write `spec.md`, `plan.md`, and `tasks.md` first and confirm them with me;
  then follow the project constitution: write the failing unit test first, show it fails, then implement
  the minimal code to pass it."
- Reject any Copilot suggestion that: adds a new third-party dependency, introduces an interface/abstraction
  with only one implementation, puts business logic inside a Swing class, skips straight to implementation
  without a preceding test, or implements behavior not present in the relevant `spec.md`.
- If a genuinely new requirement emerges that conflicts with this document (e.g. "we now need persistence"),
  update this constitution explicitly and deliberately — do not let scope drift in through ad hoc code
  changes.

---

## 13. Amendments

This constitution may be amended, but changes must be explicit:
1. Propose the change and rationale.
2. Update this document.
3. Ensure existing code/tests are brought into compliance with the amended rules before continuing new
   feature work.