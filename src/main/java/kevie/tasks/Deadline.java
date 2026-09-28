package kevie.tasks;

import kevie.exceptions.FileBadRawException;

public class Deadline extends Task{

    private String due;

    public Deadline(String name, String endTime) {
        super(name);
        this.due = endTime;
    }

    public Deadline(String[] rawArgs, int lineNo) throws FileBadRawException {
        super(rawArgs, lineNo);
        try {
            this.due = rawArgs[3];
        } catch (Exception e){
            throw new FileBadRawException(lineNo);
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
