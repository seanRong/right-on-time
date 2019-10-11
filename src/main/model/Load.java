package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Load implements Loadable {
    public void load() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("schedule.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray eventJson = (JSONArray) obj;
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

    public static String parseEventJson(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String name = (String) eventObject.get("name");

        String location = String.valueOf(eventObject.get("location"));

        String time = String.valueOf(eventObject.get("time"));

        return (name + " on " + time + " at " + location);
    }
}
