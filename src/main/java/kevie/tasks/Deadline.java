package kevie.tasks;

public class Deadline extends Task{

    private String due;

    public Deadline(String name, String endTime) {
        super(name);
        this.due = endTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (due: " + due + ")";
    }
}
