package kevie.exceptions;

import kevie.UserInterface;
import kevie.commands.Command;

public class CmdSynNoArgException extends CmdSyntaxException{
    public CmdSynNoArgException(Command command, UserInterface ui) {
        super("No arguments given", command, ui);
    }
}
