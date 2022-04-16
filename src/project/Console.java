package project;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidPinException;
import exceptions.NotEnoughCashException;
import exceptions.WrongMenuChoiceException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Console {

    private static BankAccount account;
    private static Card card;
    private static String choice;
    private static Scanner input = new Scanner(System.in);
    private static LoggerInterface logger;

    public Console(LoggerInterface logger) {
        Console.logger = logger;
    }

    public static void displayTransactionMenu() {
        System.out.print("\n" + Messages.TRANSACTION_MENU);
    }

    public static void displayWithdrawMenu() {
        System.out.print("\n" + Messages.WITHDRAW_MENU);

    }

    static void displayConsoleMenu() throws InterruptedException {

        try {

            System.out.print(Messages.WELCOME);
            card = CardReader.insertCard();
            account = Card.getBankAccount(card);

            displayTransactionMenu();
            chooseFromTransactionMenu();

            displayConsoleMenu();
        } catch (InvalidPinException | AccountNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            displayConsoleMenu();
        }
    }

    /**
     * Balance inquiry method
     *
     */
    public static void enquiryAboutBalance() {
        logger.log(Messages.Log.BALANCE_ENQUIRY);
        System.out.println(Messages.BALANCE + account.getBalance() + " $\n");
        System.out.println(Messages.DISPENSER_CASH + CashDispenser.getCash() + " $\n");
        chooseAfterBalanceEnquiry();
    }

    /**
     *
     */
    public static void chooseFromTransactionMenu() {
        try {
            choice = input.next();
            switch (choice) {
                case "1" : {
                    displayWithdrawMenu();
                    chooseFromWithdrawMenu();
                }
                case "2" :
                    enquiryAboutBalance();

                case "3" : {
                    logger.log(Messages.Log.CANCEL_TRANSACTION);
                    System.out.println(Messages.CANCEL);
                    displayConsoleMenu();
                }

                default :
                    throw new WrongMenuChoiceException();

            }
        } catch (WrongMenuChoiceException | InterruptedException e) {
            System.out.print(e.getMessage());
            chooseFromTransactionMenu();
        }

    }

    /**
     * choose amount to withdraw
     *
     */
    public static void chooseFromWithdrawMenu() {
        choice = input.next();
        int ch;
        try {

            ch = Integer.parseInt(choice);

            int amount;
            if (ch == -1) {
                logger.log(Messages.Log.CANCEL_WITHDRAW);
                System.out.println(Messages.CANCEL);
                TimeUnit.MILLISECONDS.sleep(600);
                return;

            } else if (ch == 5) {
                System.out.print(Messages.ENTER_AMOUNT);
                amount = input.nextInt();

            } else if (ch > -1 && ch < 5) {
                amount = ch * 50;

            } else {
                throw new WrongMenuChoiceException();
            }

            account.withdraw(amount);

            System.out.print(Messages.COLLECT_MONEY);
            String str = null;
            try {
                str = collectMoney(10);
            } catch (ExecutionException ex) {
                System.out.println(ex.getMessage());
            }

            if (str == null) {
                account.withdraw(amount * -1);
            }

            logger.log(Messages.Log.WITHDRAW + amount + " $ ");
            System.out.println();

            System.out.println(Messages.BALANCE + account.getBalance() + " $\n");
            System.out.println(Messages.DISPENSER_CASH + CashDispenser.getCash() + " $\n");

            displayConsoleMenu();

        } catch (NumberFormatException e) {
            System.out.print(Messages.Exceptions.WRONG_INPUT);
            chooseFromWithdrawMenu();
        } catch (InsufficientBalanceException | NotEnoughCashException | InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (WrongMenuChoiceException e) {
            System.out.print(e.getMessage());
            chooseFromWithdrawMenu();
        }

    }

    /**
     * To ensure that the user collect money in 10 seconds or less of the money
     * will restored
     *
     * @param timeout rang of time before restored money to ATM
     * @return
     * @throws ExecutionException in case something happened during waiting the
     * user to enter input
     *
     */
    public static String collectMoney(int timeout) throws ExecutionException {
        final ExecutorService e = Executors.newFixedThreadPool(1);
        Callable<String> k = () -> new Scanner(System.in).nextLine();
        LocalDateTime start = LocalDateTime.now();
        Future<String> g = e.submit(k);

        while (ChronoUnit.SECONDS.between(start, LocalDateTime.now()) < timeout) {
            if (g.isDone()) {
                try {
                    return g.get();
                } catch (InterruptedException | ExecutionException | IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    g = e.submit(k);

                }
            }
        }

        System.out.println(Messages.TIMEOUT);
        logger.log(Messages.Log.WITHDRAW_TIMEOUT);
        g.cancel(true);
        return null;
    }

    /**
     * after balance inquiry the customer has two options either exit so the
     * next person can use the ATM or back to transaction menu
     *
     */
    public static void chooseAfterBalanceEnquiry() {

        try {
            System.out.print(Messages.AFTER_ENQUIRY_MENU);
            String ch = input.next();
            switch (ch) {
                case "T" : {
                    logger.log(Messages.Log.BACK_TO_TRANSACTION);
                    displayTransactionMenu();
                    chooseFromTransactionMenu();
                }
                case "E" : {
                    logger.log(Messages.Log.EXIT_FROM_ENQUIRY);
                    System.out.println(Messages.EXIT_AFTER_ENQUIRY);
                    displayConsoleMenu();
                }
                default :
                    throw new WrongMenuChoiceException();
            }
        } catch (InterruptedException | WrongMenuChoiceException ex) {
            System.out.println(ex.getMessage() + "\n");
            chooseAfterBalanceEnquiry();
        }
    }

}
