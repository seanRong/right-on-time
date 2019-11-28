package ui;

import model.*;

import java.awt.geom.Point2D;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MakeEventUI {
    private Point2D.Double defaultPoint = new Point2D.Double(0.0, 0.0);
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    Scanner scanner = new Scanner(System.in);
    EventManager eventManager;
    EventObserver eo = new EventObserver();

    public MakeEventUI(EventManager eventManager) throws ParseException {
        this.eventManager = eventManager;
    }

    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added
    public Event makeOneTimeEvent(String n, String d, String lat, String lon) throws ParseException {
        Event newEvent = new OneTimeEvent("", defaultDate, defaultPoint);
        newEvent = setEventDetails(newEvent, n, d, lat, lon);
        newEvent.addObserver(eo);

        try {
            eventManager.dupeCheck((Event) newEvent);
            eventManager.addEvent((Event) newEvent);
            System.out.println("added one time event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        eventMade(newEvent);
        return newEvent;
    }

    public void makeEventRepeated(String n, String d, String lat, String lon) throws ParseException {
        Event newEvent = new RepeatedEvent("", defaultDate, defaultPoint);
        newEvent = setEventDetails(newEvent, n, d, lat, lon);
        newEvent.addObserver(eo);

        try {
            eventManager.dupeCheck(newEvent);
            eventManager.addEvent(newEvent);
            System.out.println("added repeated event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        eventMade(newEvent);
    }

    public void makeEventSchool(String n, String d, String lat, String lon) throws ParseException {
        ClassEvent newEvent = new ClassEvent("", defaultDate, defaultPoint);
        newEvent = (ClassEvent) setEventDetails(newEvent, n, d, lat, lon);
        newEvent.addObserver(eo);

        newEvent.setSchedule(eventManager.classSchedule);
        eventMade(newEvent);
    }

//    private Event setEventDetails(Event newEvent) throws ParseException {
//        System.out.println("Please enter event title");
//        newEvent.name = scanner.nextLine();
//        System.out.println("Please enter event date dd/MM/yyyy");
//        String rawDate = scanner.nextLine();
//        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
//        newEvent.date = date;
//        System.out.println("Please enter event location (2 doubles seperated by space)");
//        newEvent.location = new Point2D.Double(scanner.nextDouble(), scanner.nextDouble());
//        scanner.nextLine();
//        return newEvent;
//    }

    private Event setEventDetails(Event newEvent, String n, String d, String lat, String lon) throws ParseException {
        newEvent.name = n;
        String rawDate = d;
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        newEvent.date = date;
        newEvent.location = new Point2D.Double(Double.parseDouble(lat), Double.parseDouble(lon));
        return newEvent;
    }

    public void eventMade(Event newEvent) {
        System.out.println("event added, it's called: ");
        System.out.println(
                newEvent.getEventName() + " on " + newEvent.getEventDate() + " at " + newEvent.getEventLocation());
    }
}
