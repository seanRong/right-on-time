package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventManager implements Loadable, Saveable {
    private ArrayList<Event> eventList;
    private JSONArray eventJson;
    public ClassSchedule classSchedule;
    public HashMap<String, Event> fastLookup = new HashMap<String, Event>();

    public EventManager() {
        eventList = new ArrayList<Event>();
        JSONArray eventJson = new JSONArray();
        classSchedule = new ClassSchedule();
        this.eventJson = eventJson;
        load();
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public JSONArray getEventJson() {
        return this.eventJson;
    }

    public static String parseEventJson(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String name = (String) eventObject.get("name");

        String location = String.valueOf(eventObject.get("location"));

        String time = String.valueOf(eventObject.get("time"));

        return (name + " on " + time + " at " + location);
    }

    public void load() {
        //json-simple library
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("schedule.json")) {
            Object obj = jsonParser.parse(reader);

            eventJson = (JSONArray) obj;
            System.out.println("previous state");
//            System.out.println(eventJson);
            eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Nothing to read. Initializing new save.");
            eventJson = new JSONArray();
        }
    }

    public void save() {
        //json-simple library
        //Write JSON file
        try (FileWriter file = new FileWriter("schedule.json")) {

            file.write(eventJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public String loadDiff(String filename) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = new FileReader(filename);
//        Object obj = jsonParser.parse(reader);
//        eventJson = (JSONArray) obj;
//        StringBuffer eventStrings = new StringBuffer();
//        for (int i = 0; i < eventJson.size(); i++) {
//            eventStrings.append(parseEventJson((JSONObject) eventJson.get(i)));
//        }
//        return eventStrings.toString();
//    }

    //EFFECTS: prints the entire eventList, even if it's empty.
    public String dumpSchedule(ArrayList<Event> eventList) {
        System.out.println("Your schedule: ");
        eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        System.out.println("new entries this session: ");
        StringBuilder toPrint = new StringBuilder();

        for (Event event : eventList) {
            toPrint.append(event.getEventDetails());
        }

        System.out.println("school entries");
        System.out.println(dumpSchoolSchedule(classSchedule));

        return toPrint.toString();
    }


    public String dumpSchoolSchedule(ClassSchedule classSchedule) {
        String toPrint = "";

        for (int i = 0; i < classSchedule.getSize(); i++) {
            toPrint += classSchedule.getEvent(i).name;
        }
        return toPrint;
    }

    public void addEvent(Event newEvent) {
        try {
            dupeCheck(newEvent);
            absurdTimeChecker(newEvent.getEventDate());
            eventList.add(newEvent);
            JSONObject eventObject = createJsonObject(newEvent);
            eventJson.add(eventObject);
            fastLookup.put(newEvent.name, newEvent);
        } catch (TooBusyException e) {
            System.out.println(e);
        } catch (AbsurdTimeException e) {
            System.out.println(e);
        } finally {
            System.out.println("exiting add protocol");
        }
    }

    private JSONObject createJsonObject(Event newEvent) {
        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", newEvent.getEventName());
        eventDetails.put("location", newEvent.getEventLocation());
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String strDate = dateFormat.format(newEvent.date);
        eventDetails.put("time", strDate);
//        eventDetails.put("time", newEvent.getEventDate());

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        return eventObject;
    }


    public void dupeCheck(Event newEvent) throws TooBusyException {
        boolean dupe = false;
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getEventDate().equals(newEvent.getEventDate())) {
                dupe = true;
            }
        }
        if (dupe) {
            throw new TooBusyException("Too many events on one day");
        }
    }

    public void absurdTimeChecker(Date d) throws AbsurdTimeException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date curr = new Date();
        if (d.compareTo(curr) < 0) {
            throw new AbsurdTimeException("Cannot go back in time");
        }
    }

}
