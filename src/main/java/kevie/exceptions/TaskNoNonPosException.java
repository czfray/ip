package kevie.exceptions;

public class TaskNoNonPosException extends TaskNoException{
    public TaskNoNonPosException() {
        super("Task number inputted is non positive");
    }
}
