package project;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ATMLogger logger = new ATMLogger();
        ATM atm = new ATM(logger);
        atm.start();
       
    }
}
