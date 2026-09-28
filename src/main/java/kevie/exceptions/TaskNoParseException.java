package kevie.exceptions;

public class TaskNoParseException extends TaskNoException{
    public TaskNoParseException() {
        super("Task no. argument given not a number");
    }
}
