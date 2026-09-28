package kevie.exceptions;

import kevie.UserInterface;

public class TaskNoNonPosException extends TaskNoException{
    public TaskNoNonPosException(UserInterface ui) {
        super("Task number inputted is non positive", ui);
    }
}
