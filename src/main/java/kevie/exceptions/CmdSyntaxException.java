package kevie.exceptions;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.commands.Command;

public class CmdSyntaxException extends KevieException{

    Command command;

    public CmdSyntaxException(String type, Command command, UserInterface ui){
        super("Sorry, the syntax of your command is invalid", type, ui);
        this.command = command;
    }

    @Override
    public void printMessage(){
        super.printMessage();
        command.help();
    }
}
