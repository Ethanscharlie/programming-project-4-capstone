# Project Plan: Monopoly Movement Simulator

## 1․ Project Information

* **Course / Term:** CS 2430-502 - Spring 2026
* **Project:** Programming Project 4 - Capstone (PLO-CS-6)
* **Team Name:** AEB
* **Start Date:** April 20‚ 2026
* **Due Date:** May 3‚ 2026
* **Checkpoint Date:** April 26‚ 2026

## 2․ Team members and roles

### Alejandro Pasillas (Communications Lead)
* Organizes and maintains the /docs folder
* Leads the final 3–6 page professional analysis report
* Coordinates the team screencast and final submission
* Manages weekly status reports and repository organization

### Ethan Hadley (Implementation Lead)
* Primary architect for the core simulation engine
* Implements Board parsing‚ CardDeck logic‚ and Jail strategies
* Ensures code adheres to the established UML design

### Ben Paul (Verification Lead)
* Designs the verification plan for movement logic and dice rolls
* Manages batch simulation runner for $n=1,000,000$ turns
* Validates output consistency across 80 total datasets


## 3․ Repository Setup
* **Platform:** GitHub
* **Repository:** https://github.com/Ethanscharlie/programming-project-4-capstone
* **Instructor added to repository?** Yes

## 4․ Milestones and Timeline

| Milestone            | Target Date | Description                                                   |
|:---------------------|:------------|:--------------------------------------------------------------|
| Repository Setup     | April 20    | Repo documentation initialized with Plan and Contributions    |
| Checkpoint           | April 26    | /docs folder updated with UML and technical design artifacts  |
| Core Engine Complete | April 28    | All movement logic and Jail Strategies A & B implemented      |
| Data Collection      | April 30    | Final simulations run at $n=1M$ with exported datasets        |
| Final Submission     | May 3       | All materials finalized and submitted via Canvas              |

## 5․ Task List

| Task                                                        | Owner     | Due Date |
|:------------------------------------------------------------|:----------|:---------|
| Initialize /docs and Proj_4_plan.md                         | Alejandro | April 20 |
| Implement Board and CardDeck parsing from board.txt         | Ethan     | April 24 |
| Implement `Simulator` and `JailStrategy` enum logic         | Ethan     | April 26 |
| Implement TurnEngine and "3 Doubles = Jail" logic           | Ben       | April 26 |
| Finalize UML (including `Square` and `Main` interactions)   | Ethan     | April 26 |
| Code Strategy A (ImmediateExit) and B (TryForDoubles)       | Ethan     | April 27 |
| Build Simulation Runner for batch execution                 | Ben       | April 28 |
| Perform Comparative Data Analysis of strategies             | Alejandro | May 1    |
| Write 3–6 Page Formal Analysis Report                       | Alejandro | May 2    |
| Record team screencast video                                | Team      | May 2    |
| Package .zip and submit final files                         | Alejandro | May 3    |

## 6․ Verification Plan

To ensure our program works‚ we will do:
* Unit test "Go To Jail" and Chance card movement redirection
* Verify the `Square` class accurately tracks `landingCount`
* Compare `ImmediateExit` vs `TryForDoubles` results to identify convergence at $n=1M$
* Validate top-landed squares (e.g. Illinois Avenue) against known Monopoly stats
* Ensure `Simulator.simulate()` returns the correct `array[Square]` for data processing

## 7․ Risks and Plan to Handle Them

**Simulation Runtime**
Running 80 datasets at 1 million turns each may be intensive. We will optimize the engine for speed and start batch runs early to avoid last-minute delays.

**Report Requirements**
The 3–6 page professional report requires deep analysis. Alejandro will begin drafting the report structure and data visualizations as soon as the first simulations complete.

**Technical Complexity**
Monopoly movement involves many edge cases. We will use the UML diagrams to strictly define movement priority (Dice -> Square -> Card -> Result) to avoid logic errors.
