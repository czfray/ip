package kevie.tasks;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String[] rawArgs){
        super(rawArgs);

    }

    @Override
    public char getType() {
        return 'T';
    }
}
