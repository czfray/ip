package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.CmdSynNoArgException;
import kevie.exceptions.CmdSyntaxException;
import kevie.tasks.TaskList;
import kevie.tasks.Todo;

public class TodoCommand extends Command {
    public TodoCommand() {
        super("todo");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }
        Todo newTodo = new Todo(arg);
        TaskList.instance.addTask(newTodo);
        TaskList.instance.save();
        Kevie.speak("Alright I added new todo to the list: ");
        Kevie.speak(newTodo.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "todo [Description]";
    }

    @Override
    public String example() {
        return "todo CS2113 iP bug fix";
    }
}
