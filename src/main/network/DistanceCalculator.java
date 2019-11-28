package network;

import model.SaveModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

//import static model.SaveModule.parseEventJson;

//my api key for Google
//AIzaSyCi9MhJVXNBZLqEPyDIvzfrX7eRmvBZV4s

//ex by  http://zetcode.com/articles/javareadwebpage/

public class DistanceCalculator {
    private JSONArray eventJson;
    private ArrayList<String> coordinates = new ArrayList<>();
    private static JSONParser parser = new JSONParser();
    private DistanceList distanceList;
    private Point2D.Double home;

    public DistanceCalculator(JSONArray eventJson) throws IOException {
        home = new Point2D.Double(49.264364, -123.248505);
        this.eventJson = eventJson;
        this.distanceList = new DistanceList(home);
        findDistance();
//        System.out.println(findDistance());
    }

    //MOD: coordinates
    //EFF: calcs duration of time required to walk from home to each location
    public void findDistance() throws IOException {
        System.out.println("finding distance btwn first and others");
        eventJson.forEach(event -> coordinates.add(parseEventJsonLocation((JSONObject) event)));
        String homeString = home.getX() + "," + home.getY();
        for (int i = 0; i < coordinates.size(); i++) {
//            System.out.println(coordinates.get(i));
            this.distanceList.getTravelTimes().add(
                    parseGoogleJsonDistance(mapData(homeString, coordinates.get(i))));
        }

    }

    private static String parseEventJsonLocation(JSONObject eventJson) {
        JSONObject eventObject = (JSONObject) eventJson.get("event");

        String location = String.valueOf(eventObject.get("location"));

        return location;
    }

    private static String parseGoogleJsonDistance(String sb) {
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
        JSONObject distance = (JSONObject) distanceObj.get("duration");
        String distanceText = (String) distance.get("text");
        return distanceText;

    }

    private static String mapData(String firstLocation, String secondLocation) throws IOException {
        BufferedReader br = null;

        try {
            String distanceQuery = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                    + firstLocation + "&destinations=" + secondLocation + "&key=";
            URL url = new URL(distanceQuery + "AIzaSyCi9MhJVXNBZLqEPyDIvzfrX7eRmvBZV4s");
//            System.out.println(url);
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

    public DistanceList getDistanceList() {
        return distanceList;
    }

    public void setHome(Point2D.Double home) {
        this.home = home;
    }
}


