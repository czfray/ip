package kevie.exceptions;

import kevie.commands.Command;

public class CmdSynNoArgException extends CmdSyntaxException{
    public CmdSynNoArgException(Command command) {
        super("No arguments given", command);
    }
}
