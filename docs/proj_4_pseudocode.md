# Project 4 Pseudocode: Simulation Loop

## Main Simulation Logic
```text
FUNCTION simulate(numTurns, jailStrategy):
    INITIALIZE board as Array of 40 Squares
    INITIALIZE playerPosition = 0 (GO)
    INITIALIZE doublesCount = 0
    INITIALIZE inJail = false

    FOR turn FROM 1 TO numTurns:
        ROLL two six-sided dice (die1, die2)
        
        // 1. Check for 3x Doubles (Speeding)
        IF die1 == die2:
            doublesCount++
        ELSE:
            doublesCount = 0

        IF doublesCount == 3:
            playerPosition = 10 (Jail)
            inJail = true
            doublesCount = 0
            board[playerPosition].landingCount++
            CONTINUE to next turn

        // 2. Handle Movement based on Jail Status
        IF inJail:
            APPLY jailStrategy (A or B)
            IF strategy permits exit:
                inJail = false
                MOVE playerPosition by (die1 + die2)
        ELSE:
            MOVE playerPosition by (die1 + die2)
            
        // Handle Board Wrap-around
        playerPosition = playerPosition % 40

        // 3. Handle Special Squares (Re-evaluative Logic)
        IF playerPosition == 30 (Go To Jail):
            playerPosition = 10
            inJail = true
        ELSE IF playerPosition is CHANCE or COMMUNITY CHEST:
            DRAW card
            IF card is a MOVE_CARD:
                UPDATE playerPosition to Card Destination
                // If card is "Go to Jail", update inJail status
                IF playerPosition == 10: inJail = true

        // 4. Record the Final Landing (Termination Point)
        board[playerPosition].landingCount++

    RETURN board
