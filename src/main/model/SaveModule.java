package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.geom.Point2D;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaveModule {
    private ArrayList<Event> synchronizedEventList;

    public SaveModule() {
        synchronizedEventList = new ArrayList<>();
    }

    public JSONArray load() {
        //json-simple library
        JSONParser jsonParser = new JSONParser();
        JSONArray eventJson = new JSONArray();
        try (FileReader reader = new FileReader("schedule.json")) {
            Object obj = jsonParser.parse(reader);

            eventJson = (JSONArray) obj;
            System.out.println("previous state");
            System.out.println(eventJson);
            eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Nothing to read. Initializing new save.");
            eventJson = new JSONArray();
        }
        return eventJson;
    }

    public void save(JSONArray eventJson) {
        //json-simple library
        //Write JSON file
        try (FileWriter file = new FileWriter("schedule.json")) {

            file.write(eventJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject createJsonObject(Event newEvent) {
        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", newEvent.getEventName());
        eventDetails.put("location", newEvent.getEventLocation().getX() + "," + newEvent.getEventLocation().getY());
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String strDate = dateFormat.format(newEvent.date);
        eventDetails.put("time", strDate);
        eventDetails.put("type", newEvent.getEventType());

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        return eventObject;
    }

    public static String parseEventJson(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String name = (String) eventObject.get("name");

        String location = String.valueOf(eventObject.get("location"));

        String time = String.valueOf(eventObject.get("time"));

        return (name + " on " + time + " at " + location);
    }

    public static Event parseEventJsonAsEvent(JSONObject eventJson) {
        Date date = null;

        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String name = (String) eventObject.get("name");

        String location = String.valueOf(eventObject.get("location"));
        String[] temp = location.split(",");
        Point2D.Double point = new Point2D.Double(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));

        String time = String.valueOf(eventObject.get("time"));

        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(time);
        } catch (java.text.ParseException exception) {
            System.out.println("corrupted save file");
        }

        String type = String.valueOf(eventObject.get("type"));

        return genEventByType(name, date, point, type);

    }

    public static Event genEventByType(String name, Date date, Point2D.Double point, String type) {
        Event e = null;

        if (type.equals("school")) {
            e = new ClassEvent(name, date, point);
        } else if (type.equals("repeated")) {
            e = new RepeatedEvent(name, date, point);
        } else {
            e = new OneTimeEvent(name, date, point);
        }

        return e;
    }

    public ArrayList<Event> loadEventList(JSONArray eventJson) {
        eventJson.forEach(event -> synchronizedEventList.add(parseEventJsonAsEvent((JSONObject) event)));
        return synchronizedEventList;
    }
}
