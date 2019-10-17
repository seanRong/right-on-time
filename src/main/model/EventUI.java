package model;

import java.text.ParseException;
import java.util.ArrayList;

public interface EventUI {
    void enterEvents() throws ParseException, TooBusyException;

    void enterEvent() throws ParseException, TooBusyException;

    Event makeEvent() throws ParseException, TooBusyException;


}
