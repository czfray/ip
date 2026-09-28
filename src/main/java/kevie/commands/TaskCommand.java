package kevie.commands;

import kevie.UserInterface;
import kevie.exceptions.KevieException;
import kevie.tasks.TaskList;

public abstract class TaskCommand extends Command{

    protected TaskList taskList;

    public TaskCommand(String id, UserInterface ui, TaskList taskList) {
        super(id, ui);
        this.taskList = taskList;
    }
}
