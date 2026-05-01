package io.github.ethanscharlie;

public class Square {
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
