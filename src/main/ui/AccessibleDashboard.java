package ui;

import model.Event;
import model.EventInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessibleDashboard {
    private static JSONArray eventJson;
    public ArrayList<Event> eventList;

    public AccessibleDashboard() {
        eventList = new ArrayList<Event>();
        JSONArray eventJson = new JSONArray();
        this.eventJson = eventJson;
    }

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


    public static void main(String[] args) {
        new AccessibleDashboard();
    }
}
