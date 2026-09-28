package kevie.exceptions;

import kevie.Kevie;

public class FileBadRawException extends FileCorruptionException{
    public FileBadRawException(int line) {
        super("Format incorrect on line " + line);
    }

    @Override
    public void printMessage() {
        super.printMessage();
        Kevie.speak("I will delete the corrupted line as a result.", true);
    }
}
