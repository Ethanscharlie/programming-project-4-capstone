package io.github.ethanscharlie;

public class Player {
    Board board;

    JailStrategy jailStrategy;
    int location = 0;
    int consecutiveDoubles = 0;
    int getOutOfJailCards = 0;
    boolean inJail = false;

    public Player(Board board, JailStrategy jailStrategy) {
        this.board = board;
        this.jailStrategy = jailStrategy;
    }

    public void takeTurn() {
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

    public void move(int steps) {
        for (int step = 0; step < steps; step ++) location ++;
        if (location >= board.getAmountOfSquares()) location -= board.getAmountOfSquares();
        onSquareLanding();
    }

    public void moveBack(int steps) {
        for (int step = 0; step < steps; step ++) location --;
        if (location < 0) location = board.getAmountOfSquares() + location;
        onSquareLanding();
    }

    public void addGetOutOfJailFreeCard() {
        getOutOfJailCards ++;
    }

    public void moveToNearest(Square.SquareType squareType) throws Exception {
        for (int i = location; i < board.getAmountOfSquares(); i ++) {
            if (board.getSquareAtLocation(i).type == squareType) location = i;
        }

        throw new Exception("Square with type couldn't be found.");
    }

    private void onSquareLanding() {
        var square = board.getSquareAtLocation(location);
        square.landingCount ++;
        switch (square.type) {
            case GoToJail -> goToJail();
            default -> {}
        }
    }

    private void attemptToLeaveJail() {
        inJail = false;
        switch (jailStrategy) {
            case ImmediateExit -> {}
            case TryForDoubles -> {}
        }
    }

    public void goToLocation(int location) {
        this.location = location;
    }

    private void goToJail() {
        location = board.getJailLocation();
        inJail = true;
    }

    private static int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }
}
