package kevie.commands;

import kevie.Kevie;
import kevie.tasks.TaskList;

public class ListCommand extends Command {


    public ListCommand() {
        super("list");
    }

    @Override
    public boolean execute(String arg) {
        if (TaskList.instance.getLength() < 1) {
            Kevie.speak("There ain't anything in your list yet.");
            Kevie.speak("To add new tasks, do \"todo\", \"deadline\", or \"event\".", true);
            return false;
        }

        Kevie.speak("Ok my guy, here is your list:");
        TaskList.instance.printAll();
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
