package io.github.ethanscharlie;

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
            player.moveToNearest(Square.SquareType.Jail);
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
        public void apply(Player player, Board board) {
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
}
