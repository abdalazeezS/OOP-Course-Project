package project;

public class Messages {

    static final String TRANSACTION_MENU
            = "Select the transaction you want to do:\n" + "1) Withdraw\n" + "2) Balance enquiry\n" + "3) Cancel\n" + "Enter your Choice:";
    static final String WITHDRAW_MENU
            = " Choose Amount:\n" + " 1. 50\n" + " 2. 100\n" + " 3. 150\n" + " 4. 200\n" + " 5. Enter a specific amount\n" + "-1. Cancel Operation\n" + "Enter your Choice:";
    static final String AFTER_ENQUIRY_MENU = "Enter T if you want to return to Transaction Menu\n" + "Enter E if you want to exit\n" + "Enter Your Choice:";
    static final String ATM_READY = "ATM is Ready, Please insert your card";
    static final String BALANCE = "Your Balance is : ";
    static final String CARD_INSERTED = "Card Inserted Successfully :)";
    static final String CANCEL = "Cancelling Operation...\n";
    static final String COLLECT_MONEY = "Press any key to collect money: ";
    static final String DISPENSER_CASH = "Dispenser Cash : ";
    static final String ENTER_AMOUNT = "Enter Amount: ";
    static final String ENTER_CARD_NAME = "Enter Card Name: ";
    static final String ENTER_CARD_NO = "Enter Card NO: ";
    static final String ENTER_PIN = "Enter PIN: ";
    static final String EXIT_AFTER_ENQUIRY = "Thank You For trusting our services, Have a nice day\n";
    static final String TIMEOUT = "\nTime out, Money will be restored to ATM";
    static final String WELCOME = "Welcome to ATM Java Project\n";

    static class Log {

        static final String ATM_STARTED = "ATM Started";
        static final String ATM_STOPPED = "ATM Stopped";
        static final String BACK_TO_TRANSACTION = "Back From Balance Enquiry to transaction menu";
        static final String BALANCE_ENQUIRY = "Balance Enquiry";
        static final String CARD_INSERTED = "Card Inserted Successfully -> ";
        static final String CANCEL_TRANSACTION = "Cancel from Transaction menu";
        static final String CANCEL_WITHDRAW = "Cancel from withdraw menu";
        static final String EXIT_FROM_ENQUIRY = "Exit From Balance Enquiry";
        static final String INVALID_CARD_INSERTED = "Insertion Card without account in this Bank";
        static final String INVALID_PIN = "Invalid PIN insertion after 3 attempts, account -> ";
        static final String WITHDRAW = "Withdraw -> amount: ";
        static final String WITHDRAW_TIMEOUT = "Withdraw Timeout";
        static final String WRONG_PIN = "Faild to insert correct PIN";

    }

    public static class Exceptions {

        public static final String ACCOUNT_NOT_FOUND = "Account Not Found !\n";
        public static final String INSUFFICIENT_BALANCE = "Sorry, Insufficient Balance !\n";
        public static final String INVALID_PIN = "Invalid PIN, Sorry Your Card will blocked at the moment\n";
        public static final String NOT_ENOUGH_CASH = "Not Enough Cash in Cash Dispenser\n";
        public static final String WRONG_INPUT = "Wrong Input!, Try Again ";
    }
}
