# Project 4 Pseudocode: Simulation Loop

## Main Simulation Logic
```text
FUNCTION simulate(numTurns, jailStrategy):
    INITIALIZE board as Array of 40 Squares
    INITIALIZE playerPosition = 0 (GO)
    INITIALIZE doublesCount = 0

    FOR turn FROM 1 TO numTurns:
        ROLL two six-sided dice (die1, die2)
        
        IF die1 == die2:
            doublesCount++
        ELSE:
            doublesCount = 0

        IF doublesCount == 3:
            MOVE playerPosition TO 10 (Jail)
            doublesCount = 0
            CONTINUE to next turn

        IF player is IN JAIL:
            APPLY jailStrategy (A or B)
            IF strategy permits exit:
                UPDATE playerPosition
        ELSE:
            MOVE playerPosition by (die1 + die2)
            
        // Handle Board Wrap-around
        playerPosition = playerPosition % 40

        // Handle Action Squares
        IF playerPosition == 30 (Go To Jail):
            playerPosition = 10
        ELSE IF playerPosition is CHANCE or COMMUNITY CHEST:
            DRAW card and UPDATE playerPosition if card is a move-card

        // Record the landing
        board[playerPosition].landingCount++

    RETURN board
