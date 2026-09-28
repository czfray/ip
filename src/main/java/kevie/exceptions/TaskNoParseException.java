package kevie.exceptions;

import kevie.UserInterface;

public class TaskNoParseException extends TaskNoException{
    public TaskNoParseException(UserInterface ui) {
        super("Task no. argument given not a number", ui);
    }
}
