package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.*;
import kevie.tasks.TaskList;

public abstract class Command {
    private String id;
    protected UserInterface ui;

    public Command(String id, UserInterface ui){
        this.id = id;
        this.ui = ui;
    }

    public abstract boolean execute(String arg) throws KevieException;
    public abstract String syntax();
    public abstract String example();

    public void help() {
        ui.botSpeak("Correct syntax should be: \"" + syntax() + "\".", true);
        ui.botSpeak("For example: \"" + example() + "\".", true);
    }

    public String getId() {
        return id;
    }
}
