package kevie.exceptions;

import kevie.UserInterface;

public class TaskNoExceedException extends TaskNoException {
    public TaskNoExceedException(int picked, int max, UserInterface ui) {
        super("List only have " + max + " tasks but task no." + picked + " is picked", ui);
    }
}
