package model;

public class TooBusyException extends Exception {
    public TooBusyException(String errorMessage) {
        super(errorMessage);
    }
}
