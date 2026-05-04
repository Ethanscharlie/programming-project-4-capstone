# Project Plan: Monopoly Movement Simulator

**Plan Last Updated:** May 3, 2026

## 1․ Project Information

* **Course / Term:** CS 2430-502 – Spring 2026
* **Project:** Programming Project 4 – Capstone (PLO-CS-6)
* **Team Name:** AEB
* **Start Date:** April 20, 2026
* **Due Date:** May 3, 2026
* **Checkpoint Date:** April 26, 2026

## 2․ Team members and roles

### Alejandro Pasillas (Communications Lead)
* Maintains the [/docs](https://github.com/Ethanscharlie/programming-project-4-capstone/tree/main/docs) folder and coordinates final reporting.
* Leads the final 3–6 page professional analysis report.
* Coordinates the team screencast and final submission.

### Ethan Hadley (Implementation Lead)
* Primary architect for the core [Simulator](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Simulator.java) engine.
* Implements Board parsing, CardDeck logic, and Jail strategies.

### Ben Paul (Verification Lead)
* Manages the [Main.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Main.java) batch simulation runner.
* Validates output consistency across 80 total datasets.

## 3․ Repository Setup

* **Platform:** GitHub
* **Repository:** [programming-project-4-capstone](https://github.com/Ethanscharlie/programming-project-4-capstone)
* **Instructor added to repository?** Yes

## 4․ Milestones and Timeline

| Milestone | Target Date | Description | Status |
| :--- | :--- | :--- | :--- |
| Repository Setup | April 20 | Repo documentation initialized with Plan and Contributions | **DONE** |
| Checkpoint | April 26 | /docs folder updated with UML and technical design artifacts | **DONE** |
| Core Engine Complete | April 28 | All movement logic and Jail Strategies A & B implemented | **DONE** |
| Data Collection | April 30 | Final simulations run at $n=1M$ with exported datasets | **DONE** |
| Final Submission | May 3 | All materials finalized and submitted via Canvas | **DONE** |

## 5․ Task List

| Task | Owner | Due Date | Status / Evidence |
| :--- | :--- | :--- | :--- |
| Initialize /docs and Proj_4_plan.md | Alejandro | April 20 | **DONE** - [Commit cdbf911](https://github.com/Ethanscharlie/programming-project-4-capstone/commit/cdbf911) |
| Implement Board and CardDeck parsing | Ethan | April 24 | **DONE** - [Commit a083f21](https://github.com/Ethanscharlie/programming-project-4-capstone/commit/a083f21) |
| Implement `Simulator` and `JailStrategy` logic | Ethan | April 26 | **DONE** - [Commit ffa24f4](https://github.com/Ethanscharlie/programming-project-4-capstone/commit/ffa24f4) |
| Finalize Checkpoint Artifacts (UML/Design) | Ethan | April 26 | **DONE** - [Commit f2d59c1](https://github.com/Ethanscharlie/programming-project-4-capstone/commit/f2d59c1) |
| Code Strategy A and B | Ethan | April 27 | **DONE** - [Commit dfc172a](https://github.com/Ethanscharlie/programming-project-4-capstone/commit/dfc172a) |
| Build Simulation Runner | Ben | April 28 | **DONE** - [Main.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Main.java) |
| Generate 80 required datasets | Ben | May 1 | **DONE** - [Dataset Manifest](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/output/dataset_manifest.txt) |
| Create JUnit suite and document failures | Ben | May 2 | **DONE** - [pom.xml](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/pom.xml) |
| Comparative Data Analysis | Alejandro | May 1 | **DONE** - Included in Analysis Report |
| Write Formal Analysis Report | Alejandro | May 2 | **DONE** - [Analysis_Report.pdf](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/Analysis_Report.pdf) |
| Record team screencast video | Team | May 2 | **DONE** - Link provided in Canvas submission |
| Package .zip and submit final files | Alejandro | May 3 | **DONE** - [cs2430_AEB_p4.zip](https://github.com/Ethanscharlie/programming-project-4-capstone/) |

## 6․ Checkpoint Artifacts (Located in /docs)

* **[proj_4_design-notes.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/proj_4_design-notes.md)**: Details logic assumptions and Square state tracking.
* **[proj_4_pseudocode.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/proj_4_pseudocode.md)**: High-level algorithm for the core movement loop.

## 7․ Verification Plan

* **Completed:** [Main.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Main.java) produces the 80 required datasets.
* **Completed:** Verified that `Simulator.simulate()` returns board data suitable for post-processing.
* **Completed:** Compared `ImmediateExit` vs `TryForDoubles` outputs in summary tables at $n=1M$.
* **Final Check:** All files cross-referenced in [CONTRIBUTIONS.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/CONTRIBUTIONS.md).
