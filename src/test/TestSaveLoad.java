import model.EventManager;
import model.OneTimeEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSaveLoad {
    private EventManager eventManagerTest;
    private ArrayList<OneTimeEvent> testEventList;
    private OneTimeEvent event;
    private OneTimeEvent anotherEvent;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);

    public TestSaveLoad() throws ParseException {
    }

    @BeforeEach
    void runBefore() {
        testEventList = new ArrayList<OneTimeEvent>();
        event = new OneTimeEvent("test event", defaultDate, 2000);
        anotherEvent = new OneTimeEvent("second event", defaultDate, 4500);
        eventManagerTest = new EventManager();
    }

    @Test
    void testSave() {
        JSONArray eventJson = new JSONArray();

        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", anotherEvent.name);
        eventDetails.put("location", anotherEvent.location);
        eventDetails.put("time", anotherEvent.date);

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        eventJson.add(eventObject);

        String expected = "[{\"event\":{\"name\":\"second event\",\"location\":4500,\"time\":25}}]";
        assertEquals(expected, eventJson.toJSONString());
    }

    @Test
    void testLoad() {
        JSONObject eventDetails = new JSONObject();
        eventDetails.put("name", event.name);
        eventDetails.put("location", event.location);
        eventDetails.put("time", event.date);

        JSONObject eventObject = new JSONObject();
        eventObject.put("event", eventDetails);

        String parsedObj = eventManagerTest.parseEventJson((JSONObject) eventObject);

        String expected = "test event on 20 at 2000";
        assertEquals(expected, parsedObj);
    }

}
