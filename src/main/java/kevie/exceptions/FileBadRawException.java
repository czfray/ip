package kevie.exceptions;

import kevie.Kevie;
import kevie.UserInterface;

public class FileBadRawException extends FileCorruptionException{
    public FileBadRawException(int line, UserInterface ui) {
        super("Format incorrect on line " + line, ui);
    }

    @Override
    public void printMessage() {
        super.printMessage();
        ui.botSpeak("I will delete the corrupted line as a result.", true);
    }
}
