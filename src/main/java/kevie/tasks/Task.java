package kevie.tasks;

import kevie.Kevie;
import kevie.exceptions.TaskIncoRawFormatException;

public class Task {

    private final static char NOT_DONE_CHAR = '\u2610';
    private final static char DONE_CHAR = '\u2611';
    public final static String RAW_SEPERATOR = "\\\\";

    private String name;
    private boolean isDone;

    public Task(String name)
    {
        this.name = name;
        this.isDone = false;
    }

    public Task(String[] rawArgs) throws TaskIncoRawFormatException {
        try{
            this.name = rawArgs[1];
            this.isDone = Boolean.parseBoolean(rawArgs[2]);
        } catch (Exception e) {
            throw new TaskIncoRawFormatException();
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
        return "[" + getType() + "] " +  (isDone? DONE_CHAR: NOT_DONE_CHAR) + " " + name;
    }

    public String getRaw(){
        return getType() + RAW_SEPERATOR + getName() + RAW_SEPERATOR + isDone();
    }

}
