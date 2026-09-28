package kevie.exceptions;

public class FileCorruptionException extends KevieException{
    public FileCorruptionException(String type) {
        super("Save file is corrupted", type);
    }
}
