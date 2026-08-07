# HELBMaGo — Virtual Creature Simulator

HELBMaGo is a JavaFX simulation developed for the **Java IV** course at HELB. It models a population of virtual creatures ("MaGos") whose mood reacts to social-media-style messages in real time, and was built to demonstrate practical mastery of core object-oriented **design patterns**: Observer, Strategy, Factory, and Singleton.

## 🎭 Overview

Each MaGo has a happiness status in a configurable symmetric range (e.g. `[-100, 100]`). As messages appear, each creature type reacts differently, shifting its own status — and its on-screen color — accordingly.

- **Circle MaGos** react to punctuation: each `!` or `?` in a message boosts happiness; a message with none lowers it.
- **Square MaGos** react to the message's vowel/consonant ratio: above a fixed pivot value, happiness rises; below it, happiness falls.

## 🚀 Features

- **Two creature types** (Circle, Square) with independent reaction logic, built via a **Factory**.
- **Live color gradient** — every MaGo's status maps to a distinct color, smoothly interpolated from blue (saddest) through white (neutral) to red (happiest); no two status values share the same color.
- **Message feed** — auto-advances on a configurable timer, or can be forced forward manually; each valid message is shown exactly once.
- **4 pluggable message-selection strategies** (via the **Strategy** pattern):
  - `Random` — picks any valid remaining message.
  - `GoCircle` / `GoSquare` — picks the message that best improves the average status of that creature type.
  - `GoAny` — randomly targets one creature type, then optimizes for it.
- **Per-creature control window** — click a MaGo to open a dedicated window and manually override its status.
- **Responsive population view** — creature size automatically scales with population count (2–8 MaGos spawned at launch).
- **Message validation** — invalid entries (no letters, or containing raw `http://` links) are filtered out and ignored.
- **JUnit5 test suite** covering the message validation rules.

## 🧰 Tech Stack

| Technology | Role in the project |
|---|---|
| **Java** | Core language — no streams, no `Optional`, no generics, per the course's academic constraints. |
| **JavaFX** | Builds the main population view, the message panel, and the per-creature control window. A single `Timeline` drives message scrolling — no additional threads. |
| **JUnit 5** | Unit tests for message validation (spam/invalid message filtering). |
| **Maven** | Builds the project and runs the test suite via `test.sh`. |

## 🎨 Design Patterns

| Pattern | Where it's used |
|---|---|
| **Observer** | Creature status changes notify the UI to update color/position without tight coupling. |
| **Strategy** | The four message-selection modes (Random, GoCircle, GoSquare, GoAny) are interchangeable strategies. |
| **Factory** | Creature creation is abstracted so new MaGo types can be added with minimal changes elsewhere. |
| **Singleton** | Shared simulation state/configuration (e.g. status range, message pool) is centralized. |

## 📐 Academic Constraints

- A single `Timeline` — no additional threads.
- No Java Streams, no `Optional`, no generics/custom templates, no Lombok.
- No `null` in working logic; no magic constants.
- The happiness range must stay symmetric and easily reconfigurable (e.g. changing max from 100 to 50 also updates min to -50) with minimal code impact.
- `main` contains no logic beyond application startup.

## 📋 Prerequisites

- OpenJDK (version matching the course's reference environment)
- Maven
- A Linux (Ubuntu) environment is recommended — the project targets the course's VM (`JUbuntu2526_AF`)

## 🚦 How to Run

```bash
bash run.sh    # compiles and launches the application
bash test.sh   # runs the JUnit 5 test suite via Maven
```

## 👤 Author

**Waldyr Costa** — Application Development student at HELB
[GitHub](https://github.com/Costawaldyr) · [LinkedIn](https://www.linkedin.com/in/waldyr-c-b38304257/)
