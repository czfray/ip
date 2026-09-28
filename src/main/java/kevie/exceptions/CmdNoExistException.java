package kevie.exceptions;

import kevie.Kevie;
import kevie.UserInterface;

public class CmdNoExistException extends KevieException{

    public CmdNoExistException(String attempt, UserInterface ui) {
        super("Did not understand", "\"" + attempt + "\" is not a valid command", ui);
    }

    @Override
    public void printMessage() {
        super.printMessage();
        ui.botSpeak("Say \"help\" if you need a list of commands.", true);
    }
}
