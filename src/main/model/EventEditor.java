package model;

import java.util.ArrayList;

public interface EventEditor {
    void enterEvents();

    void enterEvent();

    Event makeEvent();

    void addEvent(Event newEvent);

    void editEventByName(ArrayList<Event> eventList, String eventName);
}
