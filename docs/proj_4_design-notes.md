# Project 4 Design Notes: Monopoly Simulator

## Core Data Structures

* **Square Tracking:** We utilize an array of `Square` objects to represent the 40 spaces on the Monopoly board (indices 0-39). Each object maintains a `landingCount` integer that increments only when a player's movement *terminates* on that index.
* **Jail Logic:** To handle the complexities of Jail, we implemented a `JailStrategy` enum. This decoupling allows the simulator to toggle between strategies without modifying the core movement engine.
    * **Strategy A (Immediate Exit):** The player pays the $50 fine or uses a "Get Out of Jail Free" card on the very first turn they are in jail, then moves according to the roll.
    * **Strategy B (Stay and Roll):** The player attempts to roll doubles for up to three turns. On the third unsuccessful roll, they pay the $50 and move based on that third roll.

## Logic Assumptions

1. **Movement Priority:** To ensure consistency across 1,000,000 turns, logic follows a strict hierarchy: 
   `Dice Roll` -> `Check for 3x Doubles` -> `Update Position` -> `Process Land-On Effects` (Go To Jail/Cards) -> `Final Position Termination` -> `Increment landingCount`.
2. **Three Doubles:** If a player rolls three consecutive doubles, the `TurnEngine` intercepts the movement, places the player on index **10** (Jail), and ends the turn immediately.
3. **Card Randomization:** The `CardDeck` mimics a real-world deck. It is shuffled at the start of each simulation. When a card is drawn, it is moved to the bottom of the stack. If a "Go To" card is pulled, the landing count for the *destination* square is incremented, not the Chance/Community Chest square.

## Statistical Targets
We are validating our engine against theoretical Monopoly probability models. Specifically, we expect:
* **Jail (Index 10):** To be the most frequently "visited" square due to multiple ways to enter (Go to Jail square, 3x doubles, and specific cards).
* **Illinois Avenue & B&O Railroad:** To show higher landing frequencies relative to their neighbors, serving as benchmarks for our `Simulator` accuracy.
