package io.github.ethanscharlie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Simluator {
    public static final String BOARD_FILE = "src/main/resources/board.txt";

    public enum JailStrategy {
        ImmediateExit,
        TryForDoubles
    }

    public record Square(int landingCount, String name, boolean isJail) {}

    public static Square[] simulate(JailStrategy jailStrategy) throws IOException {
        var board = newBoard();
        System.out.println(Arrays.toString(board));
        return board;
    }

    private static Square[] newBoard() throws IOException {
        var textInFile = Files.readString(Path.of(BOARD_FILE));
        var lines = textInFile.split("\n");

        var array = new Square[lines.length];
        for (var i = 0; i < lines.length; i ++) {
            var line = lines[i];
            if (line.charAt(0) == '*') {
                array[i] = new Square(0, line.substring(1), true);
            }
            else {
                array[i] = new Square(0, line, false);
            }
        }

        return array;
    }

    /////////////// TESTING //////////////////

    static void main() {
        try {
            simulate(JailStrategy.ImmediateExit);
        } catch (IOException e) {
            System.out.println("Board file not found");
        }
    }
}
