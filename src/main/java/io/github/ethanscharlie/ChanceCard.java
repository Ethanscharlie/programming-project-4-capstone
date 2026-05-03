package io.github.ethanscharlie;

import java.util.Random;

public enum ChanceCard {
    AdvanceToBoardwalk {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.goToLocation(board.getLocationOfSquareWithName("Boardwalk"));
        }
    },

    AdvanceToGo {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.goToLocation(board.getLocationOfSquareWithName("GO"));
        }
    },

    AdvanceToIllinoisAvenue {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.goToLocation(board.getLocationOfSquareWithName("Illinois Avenue"));
        }
    },

    AdvanceToStCharlesPlace {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.goToLocation(board.getLocationOfSquareWithName("St. Charles Place"));
        }
    },

    AdvanceToNearestRailroad {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.moveToNearest(Square.SquareType.Railroad);
        }
    },

    AdvanceToNearestUtility {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.moveToNearest(Square.SquareType.Utility);
        }
    },

    GetOutOfJailFree {
        @Override
        public void apply(Player player, Board board) {
            player.addGetOutOfJailFreeCard();
        }
    },

    GoBack3Spaces {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.moveBack(3);
        }
    },

    GoToJail {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    TripToReadingRailroad {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    GeneralRepairs,
    BankPaysDividend,
    SpeedingFine,
    ChairmanOfTheBoard,
    BuildingLoanMatures;

    public void apply(Player player, Board board) throws Exception {}

    private static final ChanceCard[] VALUES = values();
    private static final Random RANDOM = new Random();

    public static ChanceCard getRandom() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
