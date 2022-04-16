package project;

import java.util.ArrayList;

public class Bank {

    public static ArrayList<BankAccount> customers = new ArrayList<>();

    Bank() {
        addAccounts();
    }

    public static void addCustomer(int accountNO, String name, Card card, double balance, String pin) {
        BankAccount bankAccount = new BankAccount(accountNO, name, card, balance, pin);
        customers.add(bankAccount);
    }

    public final void addAccounts() {
        addCustomer(123456, "Ali", new Card("1", "Cash"), 5000, "1234");
        addCustomer(7891011, "Abd", new Card("2", "VISA"), 4000, "4567");
        addCustomer(12131415, "Basel", new Card("3", "Master"), 3500, "8910");
    }
}
