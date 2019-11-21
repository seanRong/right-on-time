package ui;

import model.Event;
import model.EventManager;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EditEventUI {
    private Scanner scanner = new Scanner(System.in);
    private EventManager eventManager;

    public EditEventUI(EventManager eventManager) throws ParseException {
        this.eventManager = eventManager;
        System.out.println("edit mode. enter query");
        String query = scanner.nextLine();
        editEventByName(query);
    }

    // REQUIRES: an event with the given name
    // MODIFIES: the selected event
    // EFFECTS: gives user ability to change the location and date of said event
    private void editEventByName(String eventName) throws ParseException {
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
//        scanner.nextLine();
    }

    // MODIFIES: given event's date
    private void editEventDate(Event e) throws ParseException {
        System.out.println("current date");
        System.out.println(e.date);
        String rawDate = scanner.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        e.setDate(date);
        System.out.println("new date");
        System.out.println(e.date);
        e.notifyUp();
    }

    // MODIFIES: given event's location
    private void editEventLocation(Event e) {
        System.out.println("current location");
        System.out.println(e.location);
        e.setLocation(new Point2D.Double(scanner.nextDouble(), scanner.nextDouble()));
        scanner.nextLine();
        System.out.println("new location");
        System.out.println(e.location);
        e.notifyUp();
    }
}
