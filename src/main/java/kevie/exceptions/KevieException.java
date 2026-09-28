package kevie.exceptions;

import kevie.Kevie;

public class KevieException extends Exception{

    protected String msg;
    protected String type;

    public KevieException(String msg, String type){
        this.msg = msg;
        this.type = type;
    }

    @Override
    public String toString() {
        return msg + ": " + type + ".";
    }

    public void printMessage(){
        Kevie.speak(this.toString());
    }
}
