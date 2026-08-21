# Instructions: Interactive HTML Slide Deck — "Spec-Driven Development for Enterprise-Grade AI Coding"

## Purpose
Generate a **single self-contained HTML file** that functions as an interactive slide deck for a 40-minute university lecture (20-25 min presentation + 15-20 min live demo). The presenter will open this file directly in a browser on their laptop — no server, no build step, no external dependencies that require internet access during the talk.

---

## Global Technical Requirements

- **Single HTML file**, all CSS and JS inline (no external file references). Fonts may use a CDN `<link>` with a graceful fallback to system fonts, but nothing else should depend on network access.
- **Navigation**:
  - Right Arrow / Space / click "Next" → next slide
  - Left Arrow / click "Prev" → previous slide
  - Number keys or a small slide-index dot/progress indicator to jump around (presenter may skip during demo transition)
  - `Esc` optional: toggle a slide overview/grid view if easy to implement, but not required
- Slide counter (e.g. "4 / 11") shown subtly in a corner.
- Fully keyboard-navigable, but also usable with mouse clicks only (presenter may not always have a clicker with keyboard access).
- Responsive enough for a laptop screen (design for 16:9, e.g. 1280x720 viewport baseline), but doesn't need to support mobile.
- No slide transition animations that would slow down live presenting — simple, fast fade or instant cut is fine.

## Visual Style

- **Clean and minimal** as the base (generous whitespace, clear typographic hierarchy, no clutter).
- Layered with **subtle high-tech visual accents**: soft gradients, a faint grid or dot-pattern background, subtle glow/accent borders on key elements, monospace accents for code/technical terms. Think "modern dev-tool product page" rather than "corporate PowerPoint" or "cyberpunk terminal."
- Use a restrained color palette: a dark or near-dark base with one or two accent colors (e.g., deep navy/charcoal background with an electric blue or teal accent), OR a light clean base with the same accent logic — pick one direction and apply it consistently across all slides.
- Typography: a clean sans-serif for body/headings (e.g., Inter, system-ui), monospace font (e.g., JetBrains Mono, Fira Code) for anything code-like, tool names, file paths, or workflow step labels that reference technical artifacts.
- Diagrams (Sections 3 especially) should be built as styled HTML/CSS/SVG boxes and connectors — NOT images. They must render crisply and be easy to tweak.
- Highlight "human-in-the-loop" hard-stop gates with a distinct visual treatment (different color/icon, e.g. a "stop sign" or "pause" icon) so they visually pop out from automated steps across slides 7-10 — this is a recurring visual motif and should be consistent.

## Content Tone
- Audience: 2nd-year university students. Assume familiarity with basic software dev concepts (git, testing, code review) but NOT professional enterprise workflows.
- Keep text on slides concise — these are presentation slides, not documentation. Full explanations happen verbally; slides show structure, keywords, and diagrams.
- Avoid dense paragraphs. Prefer short phrases, labeled diagram nodes, and highlighted keywords.

---

## Slide-by-Slide Content

### Slide 1 — Title
- Title: "Spec-Driven Development"
- Subtitle: "How enterprises keep AI-generated code fast, secure, and maintainable"
- Presenter name / course placeholder text (use `[Your Name]` / `[Course Name]` as placeholders)
- Visual: clean title slide with the background gradient/grid treatment establishing the visual language for the whole deck.

### Slide 2 — What is "Vibe Coding"?
- Define vibe coding: writing software by prompting an AI conversationally and iterating based on whether the output "feels right" or "looks like it works" — with little to no upfront specification, design, or systematic verification.
- Key characteristics to display (short bullet/keyword style):
  - Prompt → generate → eyeball result → repeat
  - No explicit requirements captured
  - No architectural plan
  - Success = "it runs" / "it looks right"
- Optional visual: a simple loop diagram (Prompt → Generate → Eyeball → Repeat) to visually reinforce the lack of structure.

### Slide 3 — Why It Breaks at Scale
- Header: "Fine for a prototype. Risky at enterprise scale."
- Three-column (or three-card) layout, one per pillar:
  1. **Quality** — inconsistent logic across sessions, no traceability to requirements, untested edge cases, regressions reappear
  2. **Security** — no systematic checks for secrets, vulnerable dependencies, injection risks, or insecure defaults
  3. **Maintainability** — undocumented decisions, code nobody (human or AI) can safely extend later, architecture drifts with every new prompt
- Keep each card to 3-4 short bullet points max.

### Slide 4 — Concrete Failure Pattern
- Header something like: "It works in the demo. It breaks in production."
- Short narrative/visual showing a realistic pattern, e.g.:
  - Feature is prompted and generated quickly
  - Demo looks great, ships
  - Weeks later: edge case fails in production / a security scan flags a hardcoded secret / another engineer can't safely modify the code because there's no record of *why* it was built this way
- Keep this to a punchy, visual "before / after" or timeline style rather than dense text — this slide should land emotionally with the audience, not just informationally.

### Slide 5 — What is Spec-Driven Development?
- Core definition: SDD treats the **specification as the source of truth**; code is a derived, regenerable artifact from that spec — not the other way around.
- Key idea to visually emphasize: Spec → Plan → Tasks → Code (a directional flow), contrasted implicitly with vibe coding's Prompt → Code loop from slide 2.
- Short supporting points:
  - Specs are reviewed and versioned like code
  - AI implements *against* a spec, not instead of one
  - Enables traceability: every line of code maps back to a requirement

### Slide 6 — SDD Addresses Each Pillar
- Callback to slide 3's three pillars — same three-card layout, now showing how SDD addresses each:
  1. **Quality** — specs + task breakdown + review gates before code is trusted
  2. **Security** — governance/scanning built into the pipeline, not bolted on after
  3. **Maintainability** — spec and plan persist as living documentation; project "constitution"/memory keeps decisions consistent over time
- Visually, consider linking this slide to slide 3 (same layout/colors) so the audience recognizes the callback instantly.

### Slide 7 — Enterprise Workflow: Overview
- Header: "A Full Enterprise SDD Workflow"
- Show three phases as a horizontal pipeline: **Planning → Implementation → Delivery**
- Under each phase name, show ALL of its sub-steps in miniature (small labeled chips/nodes), using the exact step lists below:
  - **Planning**: Create Feature → Read Issue Tracker → Gap Analysis → Plan → Tasks → Review
  - **Implementation**: Create Unit Tests → Implement → Code Review → Run Unit Tests → Governance
  - **Delivery**: Reconcile → Constitution → Merge → Create PR → Monitor Build → Merge PR
- Visually flag the two human-in-the-loop hard-stop gates even at this miniature scale: **Review** (end of Planning) and **Reconcile** (start of Delivery) — use the consistent "stop/pause" icon motif defined in the Visual Style section.
- This slide is a map the audience can mentally refer back to during slides 8-10.

### Slide 8 — Phase 1: Planning
- Header: "Phase 1 — Planning"
- Show as a horizontal or vertical step-by-step workflow diagram (all steps visible at once, connected by arrows), each step as a labeled node with a one-line description:
  1. **Create Feature** — an AI agent (e.g. Superpowers or similar) drafts the initial feature description
  2. **Read Issue Tracker** — fetch relevant details from the team's issue tracker (e.g. Jira) — phrase generically as "issue tracker," not Jira-specific, per instructions
  3. **Gap Analysis** — AI reviews the issue tracker details against the draft; asks clarifying questions if gaps exist
  4. **Plan** — generate the formal spec and implementation plan
  5. **Tasks** — break the spec/plan into discrete, actionable tasks
  6. **Review** *(human-in-the-loop hard stop)* — spec, plan, and tasks are reviewed by a human before any code is written
- Step 6 must use the distinct hard-stop visual treatment.

### Slide 9 — Phase 2: Implementation
- Header: "Phase 2 — Implementation"
- Same workflow-diagram style as slide 8:
  1. **Create Unit Tests** — generate test cases from the spec; confirm new tests fail and existing tests still pass (test-first, not test-after)
  2. **Implement** — generate code based on the approved spec/plan/tasks
  3. **Code Review** — an authorized human reviewer checks code against spec, plan, and tasks
  4. **Run Unit Tests** — all tests must pass; code coverage must clear the required threshold
  5. **Governance** — automated checks: static analysis (e.g. SonarQube-style), dependency/vulnerability scanning (e.g. Snyk-style), secrets detection, etc. — phrase generically, real tool names only as examples
- Emphasize visually that step 1 (tests) happens *before* step 2 (implementation) — this is a deliberate TDD ordering and worth a small visual callout (e.g. a "test-first" badge).

### Slide 10 — Phase 3: Delivery
- Header: "Phase 3 — Delivery"
- Same workflow-diagram style:
  1. **Reconcile** *(human-in-the-loop hard stop)* — diff the delivered code against spec/plan/tasks; update those artifacts if they've drifted out of sync with what was actually built
  2. **Constitution** — update the project's persistent memory/constitution if the feature introduced new standing decisions or conventions
  3. **Merge** — merge the feature branch into the main branch
  4. **Create PR** — open a pull request
  5. **Monitor Build** — CI/CD pipeline runs its checks
  6. **Merge PR / Address Comments** — resolve any PR feedback, then merge to the remote main branch
- Step 1 must use the distinct hard-stop visual treatment (consistent with slide 8's Review step).

### Slide 11 — Simplified Workflow for Today's Demo
- Header: "Today's Demo — A Scoped-Down Version"
- Framing line: this is the same core workflow, minus the enterprise scaffolding (no issue tracker, no PR/CI pipeline, everything local) — visually present it as a subset/simplified path of the full pipeline from slide 7, ideally with light visual echoes of that slide's styling so the audience connects the two.
- Show these steps as a single-row workflow diagram:
  1. **Project Setup** — local project already has its constitution/project memory pre-created
  2. **Explain Features** — describe the basic feature(s) to build
  3. **Spec** — generate the specification
  4. **Plan** — generate the implementation plan
  5. **Tasks** — break down into tasks
  6. **Write Failing Unit Tests** — generate tests first, confirm they fail
  7. **Implement** — generate the code
  8. **Verify** — run unit tests, confirm they pass
- Small footer note on the slide: "Live demo — Java, VS Code, Superpowers"
- No "next slide" content needed after this — presenter will tab out to VS Code for the live demo. This can be the last slide in the deck.

---

## Notes for the AI Generating the HTML
- Do not add a closing/summary slide — the deck ends at Slide 11; the presenter will switch to their IDE for the demo and later share a GitHub link verbally.
- Reuse a single well-defined "workflow diagram" component (steps, arrows, hard-stop styling) across slides 7, 8, 9, 10, and 11 for visual consistency — build it once as a reusable pattern rather than bespoke layouts per slide.
- Keep all wording on the issue-tracker step generic ("issue tracker") rather than naming Jira, since the theory section should stay tool-agnostic; tool names (Superpowers, Java, VS Code) are only expected to appear on Slide 11 where the demo is explicitly scoped.
- Prioritize legibility from a few meters away (this will be presented on a screen/projector) — avoid small font sizes for diagram labels.