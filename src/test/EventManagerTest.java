import model.Event;

import model.EventManager;
import model.OneTimeEvent;
import model.TooBusyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {
    private EventManager eventManagerTest;
    private ArrayList<Event> testEventList;
    private OneTimeEvent event;
    private OneTimeEvent anotherEvent;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    private String secondDateRaw = "13/12/1998";
    Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(secondDateRaw);

    public EventManagerTest() throws ParseException {
    }

    @BeforeEach
    void runBefore() {
        eventManagerTest = new EventManager();
        testEventList = new ArrayList<Event>();
        event = new OneTimeEvent("test event", defaultDate, 2000);
        anotherEvent = new OneTimeEvent("second event", secondDate, 4500);
    }

    @Test
    void testInit() {
        assertEquals(0, testEventList.size());
    }

    @Test
    void testAddEvent() throws TooBusyException {
        eventManagerTest.addEvent(event);
        assertTrue(eventManagerTest.getEventList().contains(event));
    }

    @Test
    void testDumpSchedule() {
        testEventList.add(event);
        assertEquals("test event on 20 at 2000", eventManagerTest.dumpSchedule(testEventList));
    }

    @Test
    void testSave() throws TooBusyException {
        eventManagerTest.addEvent(event);
        eventManagerTest.save();
        eventManagerTest.load();
        assertTrue(eventManagerTest.getEventList().contains(event));
    }

    @Test
    void testTooBusyException() {
        try {
            eventManagerTest.addEvent(event);
            eventManagerTest.dupeCheck(anotherEvent);
//            eventManagerTest.addEvent(anotherEvent);
        } catch (TooBusyException e) {
            fail("I was not expecting TooBusyException!");
        }

        try {
            eventManagerTest.dupeCheck(event);
            eventManagerTest.dupeCheck(event);
//            eventManagerTest.addEvent(event);
            fail("I was expecting TooBusyException!");
        } catch (TooBusyException e) {
        }
    }


}
