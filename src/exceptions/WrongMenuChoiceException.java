package exceptions;

import project.Messages;

public class WrongMenuChoiceException extends Exception {

    @Override
    public String getMessage() {
        return Messages.Exceptions.WRONG_INPUT;
    }

}
