package kevie.tasks;

import kevie.exceptions.TaskIncoRawFormatException;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String[] rawArgs) throws TaskIncoRawFormatException {
        super(rawArgs);
    }

    @Override
    public char getType() {
        return 'T';
    }
}
