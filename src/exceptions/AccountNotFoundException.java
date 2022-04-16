package exceptions;

import project.Messages;

public class AccountNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return Messages.Exceptions.ACCOUNT_NOT_FOUND;
    }
}
