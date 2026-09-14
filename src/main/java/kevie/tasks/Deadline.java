package kevie.tasks;

import kevie.exceptions.TaskIncoRawFormatException;

public class Deadline extends Task{

    private String due;

    public Deadline(String name, String endTime) {
        super(name);
        this.due = endTime;
    }

    public Deadline(String[] rawArgs) throws TaskIncoRawFormatException {
        super(rawArgs);
        try {
            this.due = rawArgs[3];
        } catch (Exception e){
            throw new TaskIncoRawFormatException();
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
