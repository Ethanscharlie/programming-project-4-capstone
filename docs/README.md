# Programming Project 4 - Monopoly Movement Simulator

## Team Information
- **Team Name:** AEB
- **Team Members:** Alejandro Pasillas, Ethan Hadley, Ben Paul
- **Course:** CS 2430-502
- **Semester:** Spring 2026

## Project Overview
This project is a stochastic simulation engine developed to calculate the steady-state landing probabilities of squares on a Monopoly board. It analyzes the statistical impact of various movement rules and player strategies (specifically "Immediate Exit" vs. "Try for Doubles" in Jail) over 1,000,000+ turns.

## Repository Contents
- [src/main/java/io/github/ethanscharlie/Simulator.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Simulator.java): Core simulation engine and movement logic.
- [src/main/java/io/github/ethanscharlie/Main.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/java/io/github/ethanscharlie/Main.java): Batch runner for generating the 80 required datasets.
- [src/test/java/io/github/ethanscharlie/SimluatorTest.java](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/test/java/io/github/ethanscharlie/SimluatorTest.java): JUnit verification tests.
- [src/main/resources/board.txt](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/src/main/resources/board.txt): Board definition used by the simulator.
- `output/`: [Generated datasets and manifest](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/output/dataset_manifest.txt).

### Audit Trail & Planning (`/docs`)
- [CONTRIBUTIONS.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/CONTRIBUTIONS.md): Evidence of role rotation and individual impact.
- [proj_4_plan.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/proj_4_plan.md): Final milestone tracking and task completion status.
- [proj_4_design-notes.md](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/docs/proj_4_design-notes.md): Architectural assumptions and jail strategy documentation.

## Environment Requirements
- **Java 25**
- **Maven 3.9+** (Configured via [pom.xml](https://github.com/Ethanscharlie/programming-project-4-capstone/blob/main/pom.xml))

## Run The Batch Simulation

### Option 1: Maven (Recommended)
From the project root:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="io.github.ethanscharlie.Main"
