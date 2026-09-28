package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.*;

public abstract class Command {

    private static final int MAX_COMMAND_NO = 100;
    private static Command[] commands = new Command[MAX_COMMAND_NO];
    private static int commands_length = 0;

    public static boolean parse(String input) throws KevieException {
        String[] inputArgs = input.trim().split(" ", 2);
        for (int i = 0; i < commands_length; i++){
            if (inputArgs[0].toLowerCase().equals(commands[i].getId())){
                String cmdInputArg = inputArgs.length < 2? null : inputArgs[1];
                return commands[i].execute(cmdInputArg);
            }
        }
        throw new CmdNoExistException(inputArgs[0].toLowerCase());
    }

    public static void listCommands(){
        for (int i = 0; i < commands_length; i++){
            Kevie.speak((i+1) + ". " + commands[i].getId() + " (" + commands[i].syntax() + ")", true);
        }
    }

    private String id;

    public Command(String id){
        this.id = id;
        commands[commands_length] = this;
        commands_length++;
    }

    public abstract boolean execute(String arg) throws KevieException;
    public abstract String syntax();
    public abstract String example();

    public void help() {
        Kevie.speak("Correct syntax should be: \"" + syntax() + "\".", true);
        Kevie.speak("For example: \"" + example() + "\".", true);
    }

    public String getId() {
        return id;
    }
}
