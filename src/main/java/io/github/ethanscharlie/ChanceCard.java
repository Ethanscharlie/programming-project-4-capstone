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
        public void apply(Player player, Board board) {
        }
    },

    AdvanceToIllinoisAvenue {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    AdvanceToStCharlesPlace {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    AdvanceToNearestRailroad {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    AdvanceToNearestUtility {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    GetOutOfJailFree {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    GoBack3Spaces {
        @Override
        public void apply(Player player, Board board) {
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
