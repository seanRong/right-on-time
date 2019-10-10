package ui;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Dashboard implements EventEditor, Loadable, Saveable {
    private static JSONArray eventJson;
    public ArrayList<Event> eventList;
    private Scanner scanner;

    // Constructs a dashboard
    // MODIFIES: this
    // EFFECTS: makes a new scanner and ArrayList. beings the enterEvents process.
    public Dashboard() {
        eventList = new ArrayList<Event>();
        scanner = new Scanner(System.in);
        JSONArray eventJson = new JSONArray();
        this.eventJson = eventJson;
        load();
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
                System.out.println(dumpSchedule(eventList));
                save();
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

    public Event makeEvent() {
        EventInterface newEvent = new OneTimeEvent("",0,0);
        System.out.println("Please enter event title");
        String title = scanner.nextLine();
        ((Event) newEvent).name = title;
        System.out.println("Please enter event date");
        int date = scanner.nextInt();
        ((Event) newEvent).date = date;
        System.out.println("Please enter event location");
        int location = scanner.nextInt();
        ((Event) newEvent).location = location;
        scanner.nextLine();

        addEvent((Event) newEvent);

        return (Event) newEvent;
    }

    public Event makeEventRepeated() {
        EventInterface newEvent = new RepeatedEvent("",0,0);
        System.out.println("Please enter event title");
        String title = scanner.nextLine();
        ((Event) newEvent).name = title;
        System.out.println("Please enter event date");
        int date = scanner.nextInt();
        ((Event) newEvent).date = date;
        System.out.println("Please enter event location");
        int location = scanner.nextInt();
        ((Event) newEvent).location = location;
        scanner.nextLine();

        addEvent((Event) newEvent);

        return (Event) newEvent;
    }

    public void addEvent(Event newEvent) {
        eventList.add(newEvent);
        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", newEvent.name);
        eventDetails.put("location", newEvent.location);
        eventDetails.put("time", newEvent.date);

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        this.eventJson.add(eventObject);
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
    public String dumpSchedule(ArrayList<Event> eventList) {
        System.out.println("Your schedule: ");
        eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        System.out.println("new entries this session: ");
        StringBuffer toPrint = new StringBuffer();

        for (int i = 0; i < eventList.size(); i++) {
            toPrint.append(eventList.get(i).getEventDetails());
        }

        return toPrint.toString();
    }

    public void save() {
        //Write JSON file
        try (FileWriter file = new FileWriter("schedule.json")) {

            file.write(eventJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("schedule.json")) {
            Object obj = jsonParser.parse(reader);

            eventJson = (JSONArray) obj;
            System.out.println("previous state");
            System.out.println(eventJson);
            eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String loadDiff(String filename) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(filename);
        Object obj = jsonParser.parse(reader);
        eventJson = (JSONArray) obj;
        StringBuffer eventStrings = new StringBuffer();
        for (int i = 0; i < eventJson.size(); i++) {
            eventStrings.append(parseEventJson((JSONObject) eventJson.get(i)));
        }
        return eventStrings.toString();
    }

    public static String parseEventJson(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String name = (String) eventObject.get("name");

        String location = String.valueOf(eventObject.get("location"));

        String time = String.valueOf(eventObject.get("time"));

        return (name + " on " + time + " at " + location);
    }


    // EFFECTS: starts program by making a new dashboard
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        new Dashboard();
    }
}
