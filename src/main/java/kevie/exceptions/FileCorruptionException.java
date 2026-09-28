package kevie.exceptions;

import kevie.UserInterface;

public class FileCorruptionException extends KevieException{
    public FileCorruptionException(String type, UserInterface ui) {
        super("Save file is corrupted", type, ui);
    }
}
