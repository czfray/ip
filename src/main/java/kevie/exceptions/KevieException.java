package kevie.exceptions;

import kevie.Kevie;
import kevie.UserInterface;

public class KevieException extends Exception{

    protected String msg;
    protected String type;
    protected UserInterface ui;

    public KevieException(String msg, String type, UserInterface ui){
        this.msg = msg;
        this.type = type;
        this.ui = ui;
    }

    @Override
    public String toString() {
        return msg + ": " + type + ".";
    }

    public void printMessage(){
        ui.botSpeak(this.toString());
    }
}
