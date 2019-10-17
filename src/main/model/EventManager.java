package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EventManager implements Loadable, Saveable {
    private ArrayList<Event> eventList;
    private JSONArray eventJson;


    public EventManager() {
        eventList = new ArrayList<Event>();
        JSONArray eventJson = new JSONArray();
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

    public void save() {
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
        StringBuffer toPrint = new StringBuffer();

        for (int i = 0; i < eventList.size(); i++) {
            toPrint.append(eventList.get(i).getEventDetails());
        }

        return toPrint.toString();
    }

    public void addEvent(Event newEvent) {
        eventList.add(newEvent);
        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", newEvent.name);
        eventDetails.put("location", newEvent.location);
        eventDetails.put("time", newEvent.date);

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        eventJson.add(eventObject);
    }

}
