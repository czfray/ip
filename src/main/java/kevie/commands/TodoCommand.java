package kevie.commands;

import kevie.Kevie;
import kevie.tasks.TaskList;
import kevie.tasks.Todo;

public class TodoCommand extends Command {
    public TodoCommand() {
        super("todo");
    }

    @Override
    public boolean execute(String arg) {
        if (arg == null){
            Kevie.speak("You need to tell me what you want to do bro!");
            return false;
        }
        Todo newTodo = new Todo(arg);
        TaskList.instance.addTask(newTodo);
        Kevie.speak("Alright I added new todo to the list: ");
        Kevie.speak(newTodo.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }
}
