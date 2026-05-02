# Programming Project 4 - Monopoly Movement Simulator

## Team Information

- **Team Name:** AEB
- **Team Members:** Alejandro Pasillas, Ethan Hadley, Ben Paul
- **Course:** CS 2430-502
- **Semester:** Spring 2026

## Repository Contents

- `src/main/java/io/github/ethanscharlie/Simulator.java`: current simulation engine
- `src/main/java/io/github/ethanscharlie/Main.java`: batch runner for Part 4 datasets
- `src/test/java/io/github/ethanscharlie/SimluatorTest.java`: JUnit verification tests
- `src/main/resources/board.txt`: board definition used by the simulator
- `output/`: generated datasets and report-ready summaries
- `docs/`: planning, design, contribution, and verification artifacts

## Environment Requirements

- Java 25
- Maven 3.9+ if you want to run through Maven

## Run The Batch Simulation

### Option 1: Plain Java

From the project root:

```bash
javac -d out $(find src/main/java -name '*.java')
java -cp out io.github.ethanscharlie.Main
```

This generates:

- `80` CSV datasets under `output/strategy_a_immediate_exit/` and `output/strategy_b_try_for_doubles/`
- `output/report_summary.md`
- `output/dataset_manifest.txt`

### Option 2: Maven

If Maven is installed:

```bash
mvn test
```

The current `pom.xml` includes JUnit 5 for the verification suite.

## Verification Notes

- The batch runner captures results at `n = 1,000`, `10,000`, `100,000`, and `1,000,000`.
- The JUnit suite is intentionally strict about assignment rules and currently exposes simulator defects.
- See `docs/verification_report.md` for current pass/fail evidence and unimplemented requirements.
