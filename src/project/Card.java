package project;

import java.util.Objects;

public class Card {
    private final String no;
    private final String name;

    Card(String no, String name) {
        this.no = no;
        this.name = name;
    }

    public static BankAccount getBankAccount(Card card) {
        for (BankAccount bankAccount : Bank.customers) {
            if (bankAccount.getCard().equals(card))
                return bankAccount;
        }
        return null;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.no, other.no)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    

}
