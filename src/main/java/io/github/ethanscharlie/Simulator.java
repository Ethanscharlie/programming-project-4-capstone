package io.github.ethanscharlie;

public class Simulator {
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
