package ui;

import model.Event;

import java.util.Scanner;
import java.util.ArrayList;

public class Dashboard {
    public ArrayList<Event> eventList;
    private Scanner scanner;

    // Constructs a dashboard
    // MODIFIES: this
    // EFFECTS: makes a new scanner and ArrayList. beings the enterEvents process.
    public Dashboard() {
        eventList = new ArrayList<Event>();
        scanner = new Scanner(System.in);
        enterEvents();
    }

    // Logic for adding events
    // EFFECTS: infinite loop that takes user input and passes input to resolver
    public void enterEvents() {
        String choice = "";
        System.out.println("Enter 'add' to add event, 'edit' to edit existing event, or 'done' to exit");
        choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        resolveChoice(choice);
    }

    // routes user based on choice string
    // EFFECTS: may print statements to sout, otherwise leads user to next method
    public void resolveChoice(String choice) {
        switch (choice) {
            case "add":
                enterEvent();
                enterEvents();
                break;
            case "done":
                dumpSchedule(eventList);
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
    public void enterEvent() {
        Event newEvent = makeEvent();
        System.out.println("event added, it's called: ");
        System.out.println(newEvent.name + " on " + newEvent.date + " at " + newEvent.location);
    }

    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added

    public Event makeEvent() {
        Event newEvent = new Event("",0,0);
        System.out.println("Please enter event title");
        String title = scanner.nextLine();
        newEvent.name = title;
        System.out.println("Please enter event date");
        int date = scanner.nextInt();
        newEvent.date = date;
        System.out.println("Please enter event location");
        int location = scanner.nextInt();
        newEvent.location = location;
        scanner.nextLine();

        addEvent(newEvent);

        return newEvent;
    }

    public void addEvent(Event newEvent) {
        eventList.add(newEvent);
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

    //EFFECTS: prints the entire eventList, even if it's empty.
    private void dumpSchedule(ArrayList<Event> eventList) {
        System.out.println("Your schedule: ");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println(eventList.get(i).getEventDetails());
        }
    }

    // EFFECTS: starts program by making a new dashboard
    public static void main(String[] args) {
        new Dashboard();
    }
}
