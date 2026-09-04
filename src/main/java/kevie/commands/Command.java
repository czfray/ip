package kevie.commands;

import kevie.Kevie;

public abstract class Command {

    private static final int MAX_COMMAND_NO = 100;
    private static Command[] commands = new Command[MAX_COMMAND_NO];
    private static int commands_length = 0;

    public static boolean scan(String input){
        String[] inputArgs = input.trim().split(" ", 2);
        for (int i = 0; i < commands_length; i++){
            if (inputArgs[0].toLowerCase().equals(commands[i].getId())){
                String cmdInputArg = inputArgs.length < 2? null : inputArgs[1];
                return commands[i].execute(cmdInputArg);
            }
        }
        Kevie.speak("I don't understand what you mean by \"" + inputArgs[0] + "\".");
        Kevie.speak("Say \"help\" if you need a list of commands.", true);
        return false;
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

    public abstract boolean execute(String arg);
    public abstract String syntax();
    public abstract String example();

    protected void help() {
        Kevie.speak("Correct syntax should be: \"" + syntax() + "\".", true);
        Kevie.speak("For example: \"" + example() + "\".", true);
    }

    public String getId() {
        return id;
    }
}
