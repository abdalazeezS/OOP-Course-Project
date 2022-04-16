package exceptions;

import project.Messages;

public class NotEnoughCashException extends Exception {

    @Override
    public String getMessage() {
        return Messages.Exceptions.NOT_ENOUGH_CASH;
    }
}
