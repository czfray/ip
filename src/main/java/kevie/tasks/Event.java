package kevie.tasks;

import kevie.UserInterface;
import kevie.exceptions.FileBadRawException;

public class Event extends Task{

    private String startTime;
    private String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String[] rawArgs, int lineNo, UserInterface ui) throws FileBadRawException {
        super(rawArgs, lineNo, ui);
        try{
            this.startTime = rawArgs[3];
            this.endTime = rawArgs[4];
        } catch (Exception e){
            throw new FileBadRawException(lineNo, ui);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime + ", to: " + endTime + ")";
    }

    @Override
    public char getType() {
        return 'E';
    }

    @Override
    public String getRaw() {
        return super.getRaw() + RAW_SEPERATOR + startTime + RAW_SEPERATOR + endTime;
    }
}
