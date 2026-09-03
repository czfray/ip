package kevie.commands;

import kevie.Kevie;
import kevie.tasks.TaskList;

public class ListCommand extends Command {

    private String ident;

    public ListCommand(String indent) {
        super("list");
        this.ident = indent;
    }

    @Override
    public boolean execute(String arg) {
        if (TaskList.instance.getLength() < 1) {
            Kevie.speak("There ain't anything in your list yet.");
            return false;
        }

        Kevie.speak("Ok my guy, here is your list:");
        TaskList.instance.printAll(ident);
        return false;
    }
}
