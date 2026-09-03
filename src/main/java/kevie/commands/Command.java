package kevie.commands;

import kevie.Kevie;

public class Command {

    private static final int MAX_COMMAND_NO = 100;
    private static Command[] commands = new Command[MAX_COMMAND_NO];
    private static int commands_length = 0;

    public static boolean scan(String input){
        String[] inputArgs = input.trim().split(" ", 2);
        boolean result = false;
        for (int i = 0; i < commands_length; i++){
            if (inputArgs[0].toLowerCase().equals(commands[i].getId())){
                String cmdInputArg = inputArgs.length < 2? null : inputArgs[1];
                return commands[i].execute(cmdInputArg);
            }
        }
        Kevie.speak("heh I dun get you bro, type something I understand.");
        return false;
    }

    private String id;

    public Command(String id){
        this.id = id;
        commands[commands_length] = this;
        commands_length++;
    }

    public boolean execute(String arg){
        Kevie.speak("If this message is displayed, I am so sorry, something is wrong with the code.");
        return false;
    }

    public String getId() {
        return id;
    }
}
