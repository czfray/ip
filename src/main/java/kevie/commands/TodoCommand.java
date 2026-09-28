package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.CmdSynNoArgException;
import kevie.exceptions.CmdSyntaxException;
import kevie.tasks.TaskList;
import kevie.tasks.Todo;

public class TodoCommand extends TaskCommand {
    public TodoCommand(UserInterface ui, TaskList taskList) {
        super("todo", ui, taskList);
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException {
        if (arg == null){
            throw new CmdSynNoArgException(this, ui);
        }
        Todo newTodo = new Todo(arg);
        taskList.addTask(newTodo);
        taskList.save(ui);
        ui.botSpeak("Alright I added new todo to the list: ");
        ui.botSpeak(newTodo.toString(), true);
        ui.botSpeak("You now have " + taskList.getLength() + " tasks.", true);
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
