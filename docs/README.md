# Programming Project 4 - Monopoly Movement Simulator

## Team Information

- **Team Name:** AEB
- **Team Members:** Alejandro Pasillas, Ethan Hadley, Ben Paul
- **Course:** CS 2430-502
- **Semester:** Spring 2026

## Repository Contents

- [src/main/java/io/github/ethanscharlie/Simulator.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Simulator.java): Core simulation engine.
- [src/main/java/io/github/ethanscharlie/Main.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Main.java): Batch runner for Part 4 datasets.
- [src/test/java/io/github/ethanscharlie/SimluatorTest.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/test/java/io/github/ethanscharlie/SimluatorTest.java): JUnit verification tests.
- [src/main/resources/board.txt](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/resources/board.txt): Board definition used by the simulator.
- `output/`: Generated datasets and report-ready summaries.
- [docs/](https://github.com/Ethanscharlie/programming-project-4-capstone/tree/main/docs): Planning, design, contribution, and verification artifacts.

## Environment Requirements

- **Java 25**
- **Maven 3.9+** (Optional for build automation)

## Run The Batch Simulation

### Option 1: Plain Java

From the project root:

```bash
javac -d out $(find src/main/java -name '*.java')
java -cp out io.github.ethanscharlie.Main
