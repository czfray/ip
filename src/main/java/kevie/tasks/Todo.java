package kevie.tasks;

import kevie.exceptions.FileBadRawException;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String[] rawArgs, int lineNo) throws FileBadRawException {
        super(rawArgs, lineNo);
    }

    @Override
    public char getType() {
        return 'T';
    }
}
