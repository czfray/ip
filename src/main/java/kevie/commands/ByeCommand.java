package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.tasks.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(UserInterface ui) {
        super("bye", ui);
    }

    @Override
    public boolean execute(String arg) {
        ui.botSpeak("Bye bye! See you later!");
        return true;
    }

    @Override
    public String syntax() {
        return "bye";
    }

    @Override
    public String example() {
        return "bye";
    }

}
