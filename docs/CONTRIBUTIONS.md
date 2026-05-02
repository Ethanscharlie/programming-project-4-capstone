# Team Contributions

**Project:** Programming Project 4 - Monopoly Movement Simulator  
**Team Name:** AEB

## Role Assignments
As required by the assignment, team roles were rotated and recorded in the project plan.

- **Communications Lead:** Alejandro Pasillas
- **Implementation Lead:** Ethan Hadley
- **Verification Lead:** Ben Paul

## Individual Contributions & Evidence

### Alejandro Pasillas (GitHub: apasillas07)
**Role:** Communications Lead

- **Responsibilities:** Managed the `/docs` folder, coordinated report assembly, and kept planning artifacts aligned with project milestones.
- **Evidence Pointers:**
  - `docs/proj_4_plan.md`
  - `docs/proj_4_design-notes.md`
  - `docs/proj_4_pseudocode.md`

### Ethan Hadley (GitHub: Ethanscharlie)
**Role:** Implementation Lead

- **Responsibilities:** Built the current simulation engine, board loading logic, player state, movement flow, and jail-handling scaffolding.
- **Evidence Pointers:**
  - `src/main/java/io/github/ethanscharlie/Simulator.java`
  - `src/main/resources/board.txt`

### Ben Paul (GitHub: Ben1078)
**Role:** Verification Lead

- **Responsibilities:** Built the batch simulation runner, generated the required 80 datasets, created the JUnit verification suite, and documented verification findings for unresolved rule gaps.
- **Evidence Pointers:**
  - Batch runner and report output:
    - `src/main/java/io/github/ethanscharlie/Main.java`
    - `output/dataset_manifest.txt`
    - `output/report_summary.md`
  - Verification test suite:
    - `src/test/java/io/github/ethanscharlie/SimluatorTest.java`
    - `pom.xml`
  - Verification findings and remaining gaps:
    - `docs/verification_report.md`
