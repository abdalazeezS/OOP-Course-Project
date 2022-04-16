package project;

public class ATM {

    private final CashDispenser cashDispenser;
    private final Bank bank;
    private final Console console;
    private final CardReader cardReader;
    private static LoggerInterface logger;

    ATM(LoggerInterface logger) {
        ATM.logger = logger;
        bank = new Bank();
        cashDispenser = new CashDispenser();
        cardReader = new CardReader(logger);
        console = new Console(logger);
    }

    /**
     * start ATM so that this event added to log file also accounts added to
     * bank and initial cash is 100_000 (hyphen is used to make number more
     * clear) then the console appear
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        logger.log(Messages.Log.ATM_STARTED);
        bank.addAccounts();
        cashDispenser.setInitialCash(100_000);
        console.displayConsoleMenu();
    }

}
