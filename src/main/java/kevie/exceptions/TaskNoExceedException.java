package kevie.exceptions;

public class TaskNoExceedException extends TaskNoException {
    public TaskNoExceedException(int picked, int max) {
        super("List only have " + max + " tasks but task no." + picked + " is picked");
    }
}
