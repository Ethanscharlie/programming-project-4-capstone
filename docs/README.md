# Programming Project 4 - Monopoly Movement Simulator

## Team Information
- Team Name: AEB
- Team Members: Alejandro Pasillas, Ethan Hadley, Benjamin Paul
- Course: CS 2430-502
- Semester: Spring 2026

## Project Overview
This project implements a simulation engine to calculate the landing probabilities of squares on a standard Monopoly board. The application parses board and card data to simulate long-term player movement, accounting for:
- Standard dice rolls and movement mechanics
- "Go To Jail" square logic and "Three Doubles" penalties
- Chance and Community Chest card redirections
- Comparative analysis of Jail Strategies (Immediate Exit vs. Try for Doubles)

## Repository Structure
- `src/main/java`: application source code (Simulator, Square, CardDeck)
- `src/main/resources`: configuration files (board.txt)
- `docs`: project planning and design artifacts
- `pom.xml`: Maven project configuration

## Requirements
- Java 17 or newer
- Maven 3.9+ recommended

## Build and Run

### Option 1: Run with Maven
From the project root:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="io.github.ethanscharlie.Simulator"
