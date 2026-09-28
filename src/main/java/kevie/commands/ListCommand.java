package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.tasks.TaskList;

public class ListCommand extends TaskCommand {


    public ListCommand(UserInterface ui, TaskList taskList) {
        super("list", ui, taskList);
    }

    @Override
    public boolean execute(String arg) {
        if (taskList.getLength() < 1) {
            ui.botSpeak("There ain't anything in your list yet.");
            ui.botSpeak("To add new tasks, do \"todo\", \"deadline\", or \"event\".", true);
            return false;
        }

        ui.botSpeak("Ok my guy, here is your list:");
        taskList.printAll(ui);
        return false;
    }

    @Override
    public String syntax() {
        return "list";
    }

    @Override
    public String example() {
        return "list";
    }
}
