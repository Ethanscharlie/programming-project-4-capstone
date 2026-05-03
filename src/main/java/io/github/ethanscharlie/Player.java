package io.github.ethanscharlie;

public class Player {
    Board board;

    JailStrategy jailStrategy;
    int location = 0;
    int consecutiveDoubles = 0;
    int getOutOfJailCards = 0;
    int getOutOfJailAttempts = 0;
    boolean inJail = false;

    /**
     * @param board a reference to a board object (should be created separately).
     * @param jailStrategy the jail exit strategy to use.
     */
    public Player(Board board, JailStrategy jailStrategy) {
        this.board = board;
        this.jailStrategy = jailStrategy;
    }

    /**
     * Simulates the player taking a turn.
     * Handles moves and jails.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    public void takeTurn() throws Exception {
        var canTakeAnotherTurn = false;

        var dice1 = rollDice();
        var dice2 = rollDice();

        if (dice1 == dice2) {
            canTakeAnotherTurn = true;
            consecutiveDoubles ++;
            if (consecutiveDoubles == 3) {
                goToJail();
                consecutiveDoubles = 0;
                return;
            }
        }
        else consecutiveDoubles = 0;

        if (inJail) {
            attemptToLeaveJail();
        }
        else {
            move(dice1 + dice2);
            if (canTakeAnotherTurn) takeTurn();
        }
    }

    /**
     * Moves forwards a number of steps.
     * Will call onSquareLanding when landing.
     * @param steps the number of steps to move.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    public void move(int steps) throws Exception {
        for (int step = 0; step < steps; step ++) location ++;
        if (location >= board.getAmountOfSquares()) location -= board.getAmountOfSquares();
        onSquareLanding();
    }

    /**
     * Moves backwards a number of steps.
     * Will call onSquareLanding when landing.
     * @param steps the number of steps to move.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    public void moveBack(int steps) throws Exception {
        for (int step = 0; step < steps; step ++) location --;
        if (location < 0) location = board.getAmountOfSquares() + location;
        onSquareLanding();
    }

    /**
     * Increases the amount of "Get out of Jail Free" cards that the player is holding.
     */
    public void addGetOutOfJailFreeCard() {
        getOutOfJailCards ++;
    }

    /**
     * Moves the player to the nearest square of a type.
     * @param squareType the type of square to move to.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    public void moveToNearest(Square.SquareType squareType) throws Exception {
        for (int i = location; i < board.getAmountOfSquares(); i ++) {
            if (board.getSquareAtLocation(i).type == squareType)  {
                location = i;
                onSquareLanding();
                return;
            }
        }

        // If behind
        for (int i = 0; i < location; i ++) {
            if (board.getSquareAtLocation(i).type == squareType) {
                location = i;
                onSquareLanding();
                return;
            }
        }

        throw new Exception("Square with type couldn't be found. " + squareType.toString());
    }

    /**
     * Runs when a square is landed on by a move function.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    private void onSquareLanding() throws Exception {
        var square = board.getSquareAtLocation(location);
        square.landingCount ++;
        switch (square.type) {
            case GoToJail -> goToJail();
            case Chest -> ChestCard.getRandom().apply(this, board);
            case Chance -> ChanceCard.getRandom().apply(this, board);
            default -> {}
        }
    }

    /**
     * Uses the given jail exit strategy to exit the jail after landing there.
     * Will set inJail to false when ready to leave and will call another turn or move automatically.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    private void attemptToLeaveJail() throws Exception {
        switch (jailStrategy) {
            case ImmediateExit -> {
                if (getOutOfJailCards > 0) {
                    getOutOfJailCards --;
                    inJail = false;
                    takeTurn();
                }
                else {
                    inJail = false;
                    takeTurn();
                }
            }

            case TryForDoubles -> {
                if (getOutOfJailCards > 0) {
                    getOutOfJailCards --;
                    inJail = false;
                    takeTurn();
                }
                else if (getOutOfJailAttempts < 3){
                    var dice1 = rollDice();
                    var dice2 = rollDice();
                    getOutOfJailAttempts ++;

                    if (dice1 == dice2) {
                        getOutOfJailAttempts = 0;
                        move(dice1 + dice2);
                        inJail = false;
                    }
                }
                else {
                    getOutOfJailAttempts = 0;
                    inJail = false;
                    takeTurn();
                }
            }
        }
    }

    /**
     * Changes the players position to the given location on the board.
     * @param location a location index on the board.
     */
    public void goToLocation(int location) {
        this.location = location;
    }

    /**
     * Changes the player location to the jail.
     */
    private void goToJail() {
        location = board.getJailLocation();
        inJail = true;
    }

    /**
     * Simulates rolling a 6 sided dice.
     * @return the value of the roll.
     */
    private static int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }
}
