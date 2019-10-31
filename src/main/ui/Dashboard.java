package ui;

import model.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class Dashboard implements EventUI {

    private Scanner scanner;
    private EventManager eventManager;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);

    // Constructs a dashboard
    // MODIFIES: this
    // EFFECTS: makes a new scanner and ArrayList. beings the enterEvents process.
    public Dashboard() throws ParseException, TooBusyException {
        scanner = new Scanner(System.in);
        eventManager = new EventManager();
        enterEvents();
    }

    // Logic for adding events
    // EFFECTS: infinite loop that takes user input and passes input to resolver
    public void enterEvents() throws ParseException, TooBusyException {
        System.out.println("Enter 'add' to add event, 'edit' to edit existing event, or 'done' to exit");
        String choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        resolveChoice(choice);
    }

    // routes user based on choice string
    // EFFECTS: may print statements to sout, otherwise leads user to next method
    public void resolveChoice(String choice) throws ParseException, TooBusyException {
        switch (choice) {
            case "add":
                enterEvent();
                enterEvents();
                break;
            case "done":
                System.out.println(eventManager.dumpSchedule(eventManager.getEventList()));
                eventManager.save();
                System.exit(0);
                break;
            case "edit":
                editMode();
                enterEvents();
                break;
            default:
                System.out.println("unrecognized input");
                enterEvents();
        }
    }

    // adds a singular event to the ArrayList
    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added
    public void enterEvent() throws ParseException {
        System.out.println("Enter 'r' for repeated, 'o' for one-time, 's' for school");
        String choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        if (choice.equals("o")) {
            Event newEvent = makeEvent();
            System.out.println("event added, it's called: ");
            System.out.println(newEvent.name + " on " + newEvent.date + " at " + newEvent.location);
        } else if (choice.equals("r")) {
            Event newEvent = makeEventRepeated();
            System.out.println("event added, it's called: ");
            System.out.println(newEvent.name + " on " + newEvent.date + " at " + newEvent.location);
        } else {
            Event newEvent = makeEventSchool();
            System.out.println("event added, it's called: ");
            System.out.println(newEvent.name + " on " + newEvent.date + " at " + newEvent.location);
        }
    }

    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added
    public Event makeEvent() throws ParseException {
        EventInterface newEvent = new OneTimeEvent("", defaultDate, 0);
        System.out.println("Please enter event title");
        ((Event) newEvent).name = scanner.nextLine();
        System.out.println("Please enter event date dd/MM/yyyy");
        String rawDate = scanner.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        ((Event) newEvent).date = date;
        System.out.println("Please enter event location");
        ((Event) newEvent).location = scanner.nextInt();
        scanner.nextLine();

        try {
            eventManager.dupeCheck((Event) newEvent);
            eventManager.addEvent((Event) newEvent);
            System.out.println("added one time event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        return (Event) newEvent;
    }

    public Event makeEventRepeated() throws ParseException {
        EventInterface newEvent = new RepeatedEvent("", defaultDate,0);
        System.out.println("Please enter event title");
        ((Event) newEvent).name = scanner.nextLine();
        System.out.println("Please enter event date dd/MM/yyyy");
        String rawDate = scanner.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        ((Event) newEvent).date = date;
        System.out.println("Please enter event location");
        ((Event) newEvent).location = scanner.nextInt();
        scanner.nextLine();

        try {
            eventManager.dupeCheck((Event) newEvent);
            eventManager.addEvent((Event) newEvent);
            System.out.println("added repeated event");
        } catch (TooBusyException e) {
            System.out.println("failed to add");
        }
        return (Event) newEvent;
    }

    public Event makeEventSchool() throws ParseException {
        ClassEvent newEvent = new ClassEvent("", defaultDate,0);
        System.out.println("Please enter event title");
        ((Event) newEvent).name = scanner.nextLine();
        System.out.println("Please enter event date dd/MM/yyyy");
        String rawDate = scanner.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        ((Event) newEvent).date = date;
        System.out.println("Please enter event location");
        ((Event) newEvent).location = scanner.nextInt();
        scanner.nextLine();

        newEvent.setSchedule(eventManager.classSchedule);
        return (Event) newEvent;
    }

    public void editMode() {
        System.out.println("edit mode. enter query");
        String query = scanner.nextLine();
        editEventByName(query);
    }

    // REQUIRES: an event with the given name
    // MODIFIES: the selected event
    // EFFECTS: gives user ability to change the location and date of said event
    public void editEventByName(String eventName) {
        Event found = eventManager.fastLookup.get(eventName);
        if (!(found == null)) {
            System.out.println("set new date or location?");
            String choice = scanner.nextLine();
            System.out.println("you selected: " + choice);
            if (choice.equals("date")) {
                editEventDate(found);
            } else {
                editEventLocation(found);
            }
        } else {
            System.out.println("cannot find event of that name");
            System.out.println(eventManager.fastLookup);
        }
    }

    // MODIFIES: given event's date
    private void editEventDate(Event e) {
        System.out.println("current date");
        System.out.println(e.date);
    }

    // MODIFIES: given event's location
    private void editEventLocation(Event e) {
        System.out.println("current location");
        System.out.println(e.location);
    }


    // EFFECTS: starts program by making a new dashboard
    public static void main(String[] args) throws ParseException, TooBusyException {
        new Dashboard();
    }
}
