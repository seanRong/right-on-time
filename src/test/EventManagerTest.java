import model.Event;

import model.EventManager;
import model.OneTimeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventManagerTest {
    private EventManager eventManagerTest;
    private ArrayList<Event> testEventList;
    private OneTimeEvent event;
    private OneTimeEvent anotherEvent;


    @BeforeEach
    void runBefore() {
        eventManagerTest = new EventManager();
        testEventList = new ArrayList<Event>();
        event = new OneTimeEvent("test event", 20, 2000);
        anotherEvent = new OneTimeEvent("second event", 25, 4500);
    }

    @Test
    void testInit() {
        assertEquals(0, testEventList.size());
    }

    @Test
    void testAddEvent() {
        eventManagerTest.addEvent(event);
        assertTrue(eventManagerTest.getEventList().contains(event));
    }

    @Test
    void testDumpSchedule() {
        testEventList.add(event);
        assertEquals("test event on 20 at 2000", eventManagerTest.dumpSchedule(testEventList));
    }

    @Test
    void testSave() {
        eventManagerTest.addEvent(event);
        eventManagerTest.save();
        eventManagerTest.load();
        assertTrue(eventManagerTest.getEventList().contains(event));
    }


}
