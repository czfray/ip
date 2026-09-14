package kevie.tasks;

public class Event extends Task{

    private String startTime;
    private String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String[] rawArgs){
        super(rawArgs);
        this.startTime = rawArgs[3];
        this.endTime = rawArgs[4];

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
