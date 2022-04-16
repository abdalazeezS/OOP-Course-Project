package project;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import exceptions.AccountNotFoundException;
import exceptions.InvalidPinException;

public class CardReader {

    private static BankAccount account;
    private static Card card;
    private static final Scanner input = new Scanner(System.in);
    private static LoggerInterface logger;

    public CardReader(LoggerInterface logger) {
        CardReader.logger = logger;
    }

    public static Card insertCard() throws InterruptedException, InvalidPinException, AccountNotFoundException {
        //sleep for 0.6 s
        TimeUnit.MILLISECONDS.sleep(600);

        System.out.println(Messages.ATM_READY);

        System.out.print(Messages.ENTER_CARD_NAME);
        String name = input.next();

        System.out.print(Messages.ENTER_CARD_NO);
        String no = input.next();

        System.out.println(Messages.CARD_INSERTED);

        card = new Card(no, name);
        TimeUnit.MILLISECONDS.sleep(600);

        isExist(card);
  
        account = Card.getBankAccount(card);

        insertPin();
        logger.log(Messages.Log.CARD_INSERTED + account.getInfo());
        return card;

    }

    /**
     * method to check if the pin number of the inserted card is true or not
     *
     * @param account account that have the inserted card
     * @param pin pin that user tried to enter it
     * @return true/false depending on the real value stored in the bank
     */
    public static boolean isVerifiedCard(BankAccount account, String pin) {
        for (BankAccount b : Bank.customers) {
            if (b.equals(account) && b.getPin().equals(pin)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method to check if the inserted card exist in the bank or not
     *
     * @param card inserted card
     * @throws exceptions.AccountNotFoundException
     */
    public static void isExist(Card card) throws AccountNotFoundException {

        boolean exist = false;
        for (BankAccount b : Bank.customers) {
            if (b.getCard().equals(card)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            logger.log(Messages.Log.INVALID_CARD_INSERTED);
            throw new AccountNotFoundException();
        }

    }

    /**
     * inset pin number and check its validity and restrict attempts to 3
     *
     * @throws exceptions.InvalidPinException
     */
    public static void insertPin() throws InvalidPinException {
        String pin;
        int attempts = 2;

        System.out.print("\n" + Messages.ENTER_PIN);
        pin = input.next();

        boolean valid = CardReader.isVerifiedCard(account, pin);

        while (attempts > 0 && !valid) {
            System.out.print("Wrong PIN !, Try Again you have " + attempts
                    + " attempts: ");
            pin = input.next();
            if (CardReader.isVerifiedCard(account, pin)) {
                valid = true;
            }
            attempts--;

        }

        if (!valid) {
            logger.log(Messages.Log.INVALID_PIN + account.getInfo());
            Bank.customers.removeIf(b -> b.getCard().equals(card));
            throw new InvalidPinException();
        }
    }

}
