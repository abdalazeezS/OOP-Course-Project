package exceptions;

import project.Messages;

public class InvalidPinException extends Exception {

    @Override
    public String getMessage() {
        return Messages.Exceptions.INVALID_PIN;
    }
}
