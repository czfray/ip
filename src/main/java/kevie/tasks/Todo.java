package kevie.tasks;

import kevie.UserInterface;
import kevie.exceptions.FileBadRawException;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String[] rawArgs, int lineNo, UserInterface ui) throws FileBadRawException {
        super(rawArgs, lineNo, ui);
    }

    @Override
    public char getType() {
        return 'T';
    }
}
