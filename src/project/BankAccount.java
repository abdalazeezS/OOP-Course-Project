package project;

import java.util.Objects;
import exceptions.InsufficientBalanceException;
import exceptions.NotEnoughCashException;

public class BankAccount {

    private int accountNo;
    private String customerName;
    private Card card;
    private double balance;
    private String pin;

    BankAccount() {
    }

    public BankAccount(int accountNo, String customerName, Card card, double balance, String pin) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.card = card;
        this.balance = balance;
        this.pin = pin;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Card getCard() {
        return card;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public String getInfo() {
        return "AccountNo: " + accountNo + ", Customer Name: "
                + customerName + ", Card Name: " + card.getName() + ", Card NO: " + card.getNo() + " ";
    }

    public void withdraw(double amount) throws NotEnoughCashException, InsufficientBalanceException {

        if (this.balance < amount) {
            throw new InsufficientBalanceException();
        }
        CashDispenser.dispenseCash(amount);
        this.balance -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        return getAccountNo() == that.getAccountNo()
                && Double.compare(that.getBalance(), getBalance()) == 0
                && Objects.equals(getCustomerName(), that.getCustomerName())
                && Objects.equals(getCard(), that.getCard());
    }

}
