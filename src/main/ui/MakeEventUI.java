package ui;

import model.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MakeEventUI {
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    Scanner scanner = new Scanner(System.in);
    EventManager eventManager;

    public MakeEventUI(EventManager eventManager) throws ParseException {
        this.eventManager = eventManager;
    }

    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added
    public void makeOneTimeEvent() throws ParseException {
        Event newEvent = new OneTimeEvent("", defaultDate, 0);
        newEvent = setEventDetails(newEvent);

        try {
            eventManager.dupeCheck((Event) newEvent);
            eventManager.addEvent((Event) newEvent);
            System.out.println("added one time event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        eventMade(newEvent);
    }

    public void makeEventRepeated() throws ParseException {
        Event newEvent = new RepeatedEvent("", defaultDate,0);
        newEvent = setEventDetails(newEvent);

        try {
            eventManager.dupeCheck((Event) newEvent);
            eventManager.addEvent((Event) newEvent);
            System.out.println("added repeated event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        eventMade(newEvent);
    }

    public void makeEventSchool() throws ParseException {
        ClassEvent newEvent = new ClassEvent("", defaultDate,0);
        newEvent = (ClassEvent) setEventDetails(newEvent);

        newEvent.setSchedule(eventManager.classSchedule);
        eventMade(newEvent);
    }

    public Event setEventDetails(Event newEvent) throws ParseException {
        System.out.println("Please enter event title");
        newEvent.name = scanner.nextLine();
        System.out.println("Please enter event date dd/MM/yyyy");
        String rawDate = scanner.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        newEvent.date = date;
        System.out.println("Please enter event location");
        newEvent.location = scanner.nextInt();
        scanner.nextLine();
        return newEvent;
    }

    public void eventMade(Activity newEvent) {
        System.out.println("event added, it's called: ");
        System.out.println(
                newEvent.getEventName() + " on " + newEvent.getEventDate() + " at " + newEvent.getEventLocation());
    }
}
