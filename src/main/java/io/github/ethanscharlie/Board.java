package io.github.ethanscharlie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Board {
    public static final String BOARD_FILE = "src/main/resources/board.txt";

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
