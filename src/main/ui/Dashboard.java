package ui;

import model.*;
import org.json.simple.JSONObject;

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
        String choice = "";
        System.out.println("Enter 'add' to add event, 'edit' to edit existing event, or 'done' to exit");
        choice = scanner.nextLine();
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
                System.out.println("edit mode TODO");
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
    public void enterEvent() throws ParseException, TooBusyException {
        String choice = "";
        System.out.println("Enter 'r' for repeated, 'o' for one-time");
        choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        if (choice.equals("o")) {
            Event newEvent = makeEvent();
            System.out.println("event added, it's called: ");
            System.out.println(newEvent.name + " on " + newEvent.date + " at " + newEvent.location);
        } else {
            Event newEvent = makeEventRepeated();
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


    // REQUIRES: an event with the given name
    // MODIFIES: the selected event
    // EFFECTS: gives user ability to change the location and date of said event
    public void editEventByName(ArrayList<Event> eventList, String eventName) {
        ArrayList<String> eventNames;
        eventNames = new ArrayList<String>();
        for (int i = 0; i < eventList.size(); i++) {
            eventNames.add(eventList.get(i).getEventName());
        }
        if (eventNames.contains(eventName)) {
            System.out.println("set new date or location?");
            String choice = scanner.nextLine();
            System.out.println("you selected: " + choice);
            if (choice.equals("date")) {
                editEventDate();
            } else {
                editEventLocation();
            }
        } else {
            System.out.println("cannot find event of that name");
        }
    }

    // MODIFIES: given event's date
    private void editEventDate() {
        System.out.println("todo");
    }

    // MODIFIES: given event's location
    private void editEventLocation() {
        System.out.println("todo");
    }

    // EFFECTS: starts program by making a new dashboard
    public static void main(String[] args) throws ParseException, TooBusyException {
        new Dashboard();
    }
}
