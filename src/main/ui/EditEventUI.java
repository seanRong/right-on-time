package ui;

import model.Event;
import model.EventManager;

import java.util.*;

public class EditEventUI {
    Scanner scanner = new Scanner(System.in);
    EventManager eventManager;

    public EditEventUI(EventManager eventManager) {
        this.eventManager = eventManager;
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
        scanner.close();
    }

    // MODIFIES: given event's location
    private void editEventLocation(Event e) {
        System.out.println("current location");
        System.out.println(e.location);
        scanner.close();
    }
}
