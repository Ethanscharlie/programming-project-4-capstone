package io.github.ethanscharlie;

import java.util.Random;

public enum ChestCard {
    AdvanceToGo {
        @Override
        public void apply(Player player, Board board) throws Exception {
            player.goToLocation(board.getLocationOfSquareWithName("GO"));
        }
    },

    GoToJail {
        @Override
        public void apply(Player player, Board board) {
            player.goToLocation(board.getJailLocation());
        }
    },

    GetOutOfJailFree {
        @Override
        public void apply(Player player, Board board) {
            player.addGetOutOfJailFreeCard();
        }
    },

    DoctorsFee,
    FromSaleOfStock,
    HolidayFundMatures,
    ItsYourBirthday,
    LifeInsuranceMatures,
    PayHospitalFees,
    PaySchoolFees,
    ReceiveConsultancyFee,
    AssessedForStreetRepair,
    WonPrize,
    YouInherit,
    BankErrorInYourFavor;

    public void apply(Player player, Board board) throws Exception {}

    private static final ChestCard[] VALUES = values();
    private static final Random RANDOM = new Random();

    public static ChestCard getRandom() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
