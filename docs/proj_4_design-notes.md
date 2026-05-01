# Project 4 Design Notes: Monopoly Simulator

## Core Data Structures
* **Square Tracking:** We utilize an array of `Square` objects to represent the 40 spaces on the Monopoly board. Each object maintains a `landingCount` integer that increments every time a player's movement ends on that index.
* **Jail Logic:** To handle the complexities of Jail, we implemented a `JailStrategy` enum. This allows the simulator to toggle between Strategy A (paying/using a card immediately) and Strategy B (staying in jail to roll for doubles) without modifying the core movement engine.

## Logic Assumptions
1. **Movement Priority:** Logic follows the order of: Dice Roll -> Position Update -> Action Square Check (Go To Jail/Cards) -> Final Position Increment.
2. **Three Doubles:** The "Three Doubles sends you to Jail" rule is handled within the `TurnEngine` before the piece is moved.
3. **Card Randomization:** The `CardDeck` is shuffled at the start of each million-turn simulation to ensure statistical variance, mimicking a real-world deck reset.

## Statistical Targets
We are validating our engine by checking if the "Illinois Avenue" and "B&O Railroad" squares show the expected higher landing frequencies consistent with standard Monopoly probability models.
