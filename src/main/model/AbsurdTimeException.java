package model;

public class AbsurdTimeException extends Exception {

    //EFF: displays an absurd time exception with error msg in console
    public AbsurdTimeException(String errorMessage) {
        super(errorMessage);
    }
}
