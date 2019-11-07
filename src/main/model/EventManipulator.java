package model;

import java.text.ParseException;
import java.util.ArrayList;

public interface EventManipulator {
    void modeChoice() throws ParseException, TooBusyException;

    void enterEvent() throws ParseException, TooBusyException;

    void editMode() throws ParseException, TooBusyException;

}
