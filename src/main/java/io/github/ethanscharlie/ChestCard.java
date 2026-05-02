package io.github.ethanscharlie;

public enum ChestCard {
    AdvanceToGo {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    GoToJail {
        @Override
        public void apply(Player player, Board board) {
        }
    },

    GetOutOfJailFree {
        @Override
        public void apply(Player player, Board board) {
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
}
