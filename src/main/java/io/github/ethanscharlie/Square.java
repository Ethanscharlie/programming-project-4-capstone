package io.github.ethanscharlie;

public class Square {
    public enum SquareType {
        Generic,
        Jail,
        GoToJail,
        Chance,
        Chest,
        Utility,
        Railroad
    }

    public SquareType type;
    public String name;
    public int landingCount;

    public Square(String name) {
        this.name = name;

        if (name.equalsIgnoreCase("In Jail (Just Visiting)")) type = Square.SquareType.Jail;
        else if (name.equalsIgnoreCase("Go to Jail")) type = Square.SquareType.Jail;
        else if (name.equalsIgnoreCase("Chance")) type = Square.SquareType.Chance;
        else if (name.equalsIgnoreCase("Community Chest")) type = Square.SquareType.Chest;

        else if (name.equalsIgnoreCase("Reading Railroad")) type = SquareType.Railroad;
        else if (name.equalsIgnoreCase("Pennsylvania Railroad")) type = SquareType.Railroad;
        else if (name.equalsIgnoreCase("B&O Railroad")) type = SquareType.Railroad;
        else if (name.equalsIgnoreCase("Short Line Railroad")) type = SquareType.Railroad;

        else if (name.equalsIgnoreCase("Electric Company")) type = SquareType.Utility;
        else if (name.equalsIgnoreCase("Water Works")) type = SquareType.Utility;

        else type = SquareType.Generic;
    }
}
