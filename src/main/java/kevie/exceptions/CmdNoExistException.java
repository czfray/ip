package kevie.exceptions;

import kevie.Kevie;

public class CmdNoExistException extends KevieException{

    public CmdNoExistException(String attempt) {
        super("Did not understand", "\"" + attempt + "\" is not a valid command");
    }

    @Override
    public void printMessage() {
        super.printMessage();
        Kevie.speak("Say \"help\" if you need a list of commands.", true);
    }
}
