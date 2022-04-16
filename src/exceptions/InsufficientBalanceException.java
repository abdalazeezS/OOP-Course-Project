package exceptions;

import project.Messages;

public class InsufficientBalanceException extends Exception {

    @Override
    public String getMessage() {
        return Messages.Exceptions.INSUFFICIENT_BALANCE;

    }

}
