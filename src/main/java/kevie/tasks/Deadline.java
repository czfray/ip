package kevie.tasks;

import kevie.UserInterface;
import kevie.exceptions.FileBadRawException;

public class Deadline extends Task{

    private String due;

    public Deadline(String name, String endTime) {
        super(name);
        this.due = endTime;
    }

    public Deadline(String[] rawArgs, int lineNo, UserInterface ui) throws FileBadRawException {
        super(rawArgs, lineNo, ui);
        try {
            this.due = rawArgs[3];
        } catch (Exception e){
            throw new FileBadRawException(lineNo, ui);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (due: " + due + ")";
    }

    @Override
    public String getRaw() {
        return super.getRaw() + RAW_SEPERATOR + due;
    }

    @Override
    public char getType() {
        return 'D';
    }
}
