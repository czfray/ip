package kevie.tasks;

public class Deadline extends Task{

    private String due;

    public Deadline(String name, String endTime) {
        super(name);
        this.due = endTime;
    }

    public Deadline(String[] rawArgs){
        super(rawArgs);
        this.due = rawArgs[3];

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
