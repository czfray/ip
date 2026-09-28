package kevie.tasks;

import kevie.exceptions.FileBadRawException;

public class Task {

    private final static String NOT_DONE_CHAR = "[ ]";
    private final static String DONE_CHAR = "[V]";
    public final static String RAW_SEPERATOR = "\\\\";

    private String name;
    private boolean isDone;

    public Task(String name)
    {
        this.name = name;
        this.isDone = false;
    }

    public Task(String[] rawArgs, int lineNo) throws FileBadRawException {
        try{
            this.name = rawArgs[1];
            this.isDone = Boolean.parseBoolean(rawArgs[2]);
        } catch (Exception e) {
            throw new FileBadRawException(lineNo);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public char getType(){
        return 'X';
    }

    @Override
    public String toString() {
        return  (isDone? DONE_CHAR: NOT_DONE_CHAR) + " [" + getType() + "]" + " " + name;
    }

    public String getRaw(){
        return getType() + RAW_SEPERATOR + getName() + RAW_SEPERATOR + isDone();
    }

}
