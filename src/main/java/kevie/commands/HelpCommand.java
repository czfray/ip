package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.tasks.TaskList;

public class HelpCommand extends Command{

    private Parser parser;

    public HelpCommand(UserInterface ui, Parser parser) {
        super("help", ui);
        this.parser = parser;
    }

    @Override
    public boolean execute(String arg) {
        ui.botSpeak("Here is all the list of commands and their corresponding syntax: ");
        parser.listCommands();
        return false;
    }

    @Override
    public String syntax() {
        return "help";
    }

    @Override
    public String example() {
        return "help";
    }
}
