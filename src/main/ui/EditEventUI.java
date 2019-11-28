package ui;

import model.Event;
import model.EventManager;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EditEventUI {
    private EventManager eventManager;

    public EditEventUI(EventManager eventManager) throws ParseException {
        this.eventManager = eventManager;
    }

    // REQUIRES: an event with the given name
    // MODIFIES: the selected event
    // EFFECTS: gives user ability to change the location and date of said event
    public Event editEventByName(String eventName) throws ParseException {
        Event found = eventManager.fastLookup.get(eventName);
        return found;
    }

    // MODIFIES: given event's date
    public void editEventDate(Event e, String rawDate) throws ParseException {
        System.out.println("current date");
        System.out.println(e.date);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        e.setDate(date);
        System.out.println("new date");
        System.out.println(e.date);
    }

    // MODIFIES: given event's location
    public void editEventLocation(Event e, String location) {
        System.out.println("current location");
        System.out.println(e.location);
        String[] temp = location.split(",");
        e.setLocation(new Point2D.Double(Double.parseDouble(temp[0]), Double.parseDouble(temp[1])));
        System.out.println("new location");
        System.out.println(e.location);
    }

    public void deleteEvent(Event foundEvent) {
        eventManager.getEventList().remove(foundEvent);
        eventManager.fastLookup.remove(foundEvent.name);
    }
}
