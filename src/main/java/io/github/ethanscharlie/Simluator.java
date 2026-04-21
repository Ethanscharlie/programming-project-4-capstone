package io.github.ethanscharlie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Simluator {
    public static final String BOARD_FILE = "src/main/resources/board.txt";

    public enum JailStrategy {
        ImmediateExit,
        TryForDoubles
    }

    public static class Square {
        public enum SquareType {
            Generic,
            Jail,
            GoToJail
        }

        public SquareType type;
        public String name;
        public int landingCount;

        public Square(String name) {
            this.name = name;

            if (name.equalsIgnoreCase("In Jail (Just Visiting)")) type = Square.SquareType.Jail;
            if (name.equalsIgnoreCase("Go to Jail")) type = Square.SquareType.Jail;
            else type = SquareType.Generic;
        }
    }

    public static class Board {
        Square[] squares;
        int jailLocation;

        public Board() throws IOException {
            var textInFile = Files.readString(Path.of(BOARD_FILE));
            var lines = textInFile.split("\n");

            squares = new Square[lines.length];
            for (var i = 0; i < lines.length; i ++) {
                var line = lines[i];
                squares[i] = new Square(line);

                if (squares[i].type == Square.SquareType.Jail) jailLocation = i;
            }
        }

        public int getAmountOfSquares() { return squares.length; }

        public Square getSquareAtLocation(int location) { return squares[location]; }

        public int getJailLocation() { return jailLocation; }
    }

    public static class Player {
        Board board;

        JailStrategy jailStrategy;
        int location = 0;
        int consecutiveDoubles = 0;
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

        private void move(int steps) {
            for (int step = 0; step < steps; step ++) location ++;
            if (location >= board.getAmountOfSquares()) location -= board.getAmountOfSquares();

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

        private void goToJail() {
            location = board.getJailLocation();
            inJail = true;
        }

        private static int rollDice() {
            return (int)(Math.random() * 6) + 1;
        }
    }

    public static Board simulate(int turns, JailStrategy jailStrategy) throws Exception {
        var board = new Board();
        var player = new Player(board, jailStrategy);
        for (int turn = 0; turn < turns; turn ++) player.takeTurn();
        return board;
    }

    /////////////// TESTING //////////////////

    static void main() throws Exception {
        var board = simulate(1000, JailStrategy.ImmediateExit);
        for (int i = 0; i < board.getAmountOfSquares(); i ++) {
            var square = board.getSquareAtLocation(i);
            System.out.printf("%s: %d%n", square.name, square.landingCount);
        }
    }
}
