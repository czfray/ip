package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.CmdNoExistException;
import kevie.exceptions.KevieException;
import kevie.tasks.TaskList;

public class Parser {
    private final int MAX_COMMAND_NO = 100;
    private Command[] commands;
    private int commands_length;

    private UserInterface ui;
    private TaskList taskList;

    public Parser(UserInterface ui, TaskList taskList){
        commands = new Command[MAX_COMMAND_NO];
        commands_length = 0;
        this.ui = ui;
        this.taskList = taskList;
    }

    public void register(Command command){
        commands[commands_length] = command;
        commands_length++;
    }

    public boolean parseAndExecute(String input) throws KevieException {
        String[] inputArgs = input.trim().split(" ", 2);
        for (int i = 0; i < commands_length; i++){
            if (inputArgs[0].toLowerCase().equals(commands[i].getId())){
                String cmdInputArg = inputArgs.length < 2? null : inputArgs[1];
                return commands[i].execute(cmdInputArg);
            }
        }
        throw new CmdNoExistException(inputArgs[0].toLowerCase(), ui);
    }

    public void listCommands(){
        for (int i = 0; i < commands_length; i++){
            ui.botSpeak((i+1) + ". " + commands[i].getId() + " (" + commands[i].syntax() + ")", true);
        }
    }
}
