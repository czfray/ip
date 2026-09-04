package kevie.commands;

import kevie.Kevie;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }

    @Override
    public boolean execute(String arg) {
        Kevie.speak("Bye bye! See you later!");
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
