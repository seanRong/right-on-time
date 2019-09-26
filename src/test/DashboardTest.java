import model.Event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Dashboard;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardTest {
    private Dashboard dashboard;
    private ArrayList<Event> testEventList;
    private Event event;
    private Event anotherEvent;


    @BeforeEach
    void runBefore() {
        testEventList = new ArrayList<Event>();
        event = new Event("test event", 20, 2000);
        anotherEvent = new Event("second event", 25, 4500);
    }

    @Test
    void testInit() {
        assertEquals(0, testEventList.size());
    }

    @Test
    void testAddEvent() {
        testEventList.add(event);
        assertTrue(dashboard.eventList.contains(event));
    }

    @Test
    void testDumpSchedule() {
        testEventList.add(event);
        testEventList.add(anotherEvent);
    }

}
