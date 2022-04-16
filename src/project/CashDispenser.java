package project;

import exceptions.NotEnoughCashException;

public class CashDispenser {

    private static double cash;

    CashDispenser() {
     
    }

    public static void setInitialCash(double initialCash) {
        CashDispenser.cash = initialCash;
    }

    public static double getCash() {
        return cash;
    }

    /**
     *
     * @param amount the amount of money to withdraw
     * @throws NotEnoughCashException in case there is not enough money in
     * dispenser
     */
    public static void dispenseCash(double amount) throws NotEnoughCashException {

        if (cash < amount) {
            throw new NotEnoughCashException();
        } else {
            CashDispenser.cash -= amount;
        }
    }

}
