package kevie.exceptions;

import kevie.Kevie;

public class TaskNoException extends KevieException {

    public TaskNoException(String type) {
        super("You inputted an invalid task number", type);
    }

    @Override
    public void printMessage() {
        super.printMessage();
        Kevie.speak("To see which number correspond to your task, do \"list\".", true);
    }
}
