public class Task {

    private final static char NOT_DONE_CHAR = '\u2610';
    private final static char DONE_CHAR = '\u2611';

    private String name;
    private boolean isDone;

    public Task(String name)
    {
        this.name = name;
        this.isDone = false;
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

    @Override
    public String toString() {
        return (isDone? DONE_CHAR: NOT_DONE_CHAR) + " " + name;
    }
}
