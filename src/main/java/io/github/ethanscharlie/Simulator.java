package io.github.ethanscharlie;

public class Simulator {
    /**
     * Runs a full simulation of monopoly.
     * @param turns the total amount of turns to make the player take during the simulation.
     * @param jailStrategy the jail exit strategy to use.
     * @return a Board object containing all the needed data for the report.
     * @throws Exception can throw if a location not on the board is tried to be accessed.
     */
    public static Board simulate(int turns, JailStrategy jailStrategy) throws Exception {
        var board = new Board();
        var player = new Player(board, jailStrategy);
        for (int turn = 0; turn < turns; turn ++) player.takeTurn();
        return board;
    }

    /////////////// TESTING //////////////////

    static void main() throws Exception {
        {
            var board = simulate(10000, JailStrategy.ImmediateExit);
            for (int i = 0; i < board.getAmountOfSquares(); i++) {
                var square = board.getSquareAtLocation(i);
                System.out.printf("%s: %d%n", square.name, square.landingCount);
            }
        }

        System.out.println("\n-------------------------------------------------");

        {
            var board = simulate(10000, JailStrategy.TryForDoubles);
            for (int i = 0; i < board.getAmountOfSquares(); i++) {
                var square = board.getSquareAtLocation(i);
                System.out.printf("%s: %d%n", square.name, square.landingCount);
            }
        }
    }
}
