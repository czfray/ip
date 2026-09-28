package kevie.exceptions;

import kevie.Kevie;
import kevie.UserInterface;

public class TaskNoException extends KevieException {

    public TaskNoException(String type, UserInterface ui) {
        super("You inputted an invalid task number", type, ui);
    }

    @Override
    public void printMessage() {
        super.printMessage();
        ui.botSpeak("To see which number correspond to your task, do \"list\".", true);
    }
}
