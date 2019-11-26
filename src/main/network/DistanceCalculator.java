package network;

import model.SaveModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

//import static model.SaveModule.parseEventJson;


//AIzaSyCi9MhJVXNBZLqEPyDIvzfrX7eRmvBZV4s

//ex by  http://zetcode.com/articles/javareadwebpage/

public class DistanceCalculator {
    private JSONArray eventJson;
    private SaveModule saveModule;  //recover earlier plz
    private ArrayList<String> coordinates = new ArrayList<>();
    private static JSONParser parser = new JSONParser();


    public DistanceCalculator(JSONArray eventJson) throws IOException {
        this.eventJson = eventJson;
        findDistance();
//        System.out.println(findDistance());
    }

    private void findDistance() throws IOException {
        System.out.println("finding distance btwn first and others");
        eventJson.forEach(event -> coordinates.add(parseEventJsonLocation((JSONObject) event)));
        System.out.println(coordinates.size());
        System.out.println(coordinates.get(2));
        for (int i = 0; i < coordinates.size(); i++) {
            parseGoogleJsonDistance(mapData(coordinates.get(0), coordinates.get(i)));
        }

    }

    private static String parseEventJsonLocation(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String location = String.valueOf(eventObject.get("location"));

        return location;
    }

    private static void parseGoogleJsonDistance(String sb) {
        JSONObject json = null;

        try {
            json = (JSONObject) parser.parse(sb);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray rows = (JSONArray) json.get("rows");
        JSONObject elementsObj = (JSONObject) rows.get(0);
        JSONArray elements = (JSONArray) elementsObj.get("elements");
        JSONObject distanceObj = (JSONObject) elements.get(0);
        JSONObject distance = (JSONObject) distanceObj.get("distance");
        String distanceText = (String) distance.get("text");
        System.out.println(distanceText);

    }

    private static String mapData(String firstLocation, String secondLocation) throws IOException {
        BufferedReader br = null;

        try {
            String distanceQuery = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                    + firstLocation + "&destinations=" + secondLocation + "&key=";
            URL url = new URL(distanceQuery + "AIzaSyCi9MhJVXNBZLqEPyDIvzfrX7eRmvBZV4s");
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}


