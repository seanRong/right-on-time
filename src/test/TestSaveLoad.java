//import model.Event;
//
//import model.OneTimeEvent;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.skyscreamer.jsonassert.*;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.Dashboard;
//import ui.AccessibleDashboard;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class TestSaveLoad {
//    private Dashboard dashboard;
//    private AccessibleDashboard accdashboard;
//    private ArrayList<OneTimeEvent> testEventList;
//    private OneTimeEvent event;
//    private OneTimeEvent anotherEvent;
//    private static JSONArray eventJson;
//
//    @BeforeEach
//    void runBefore() {
//        testEventList = new ArrayList<OneTimeEvent>();
//        event = new OneTimeEvent("test event", 20, 2000);
//        anotherEvent = new OneTimeEvent("second event", 25, 4500);
//        accdashboard = new AccessibleDashboard();
////        dashboard = new Dashboard(); will hang b/c requires user input
//    }
//
//    @Test
//    void testSave() throws IOException {
//        JSONArray eventJson = new JSONArray();
//
//        JSONObject eventDetails = new JSONObject();
//        eventDetails.put("name", anotherEvent.name);
//        eventDetails.put("location", anotherEvent.location);
//        eventDetails.put("time", anotherEvent.date);
//
//        JSONObject eventObject = new JSONObject();
//        eventObject.put("event", eventDetails);
//
//        eventJson.add(eventObject);
//
//        String expected = "[{\"event\":{\"name\":\"second event\",\"location\":4500,\"time\":25}}]";
//        assertEquals(expected, eventJson.toJSONString());
//    }
//
//    @Test
//    void testLoad() {
//        JSONObject eventDetails = new JSONObject();
//        eventDetails.put("name", event.name);
//        eventDetails.put("location", event.location);
//        eventDetails.put("time", event.date);
//
//        JSONObject eventObject = new JSONObject();
//        eventObject.put("event", eventDetails);
//
//        String parsedObj = dashboard.parseEventJson((JSONObject) eventObject);
//
//        String expected = "test event on 20 at 2000";
//        assertEquals(expected, parsedObj);
//    }
//
//    @Test
//    void testLoadFileDiff() throws IOException, ParseException {
//        assertEquals("swim on 2000 at 10",
//                accdashboard.loadDiff("testSchedule.json"));
//    }
//
//}
