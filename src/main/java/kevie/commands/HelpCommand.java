package kevie.commands;

import kevie.Kevie;

public class HelpCommand extends Command{

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(String arg) {
        Kevie.speak("Here is all the list of commands and their corresponding syntax: ");
        Command.listCommands();
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
