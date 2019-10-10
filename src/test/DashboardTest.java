import model.Event;

import model.OneTimeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AccessibleDashboard;
import ui.Dashboard;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardTest {
    private Dashboard dashboard;
    private AccessibleDashboard accdashboard;
    private ArrayList<Event> testEventList;
    private OneTimeEvent event;
    private OneTimeEvent anotherEvent;


    @BeforeEach
    void runBefore() {
        accdashboard = new AccessibleDashboard();
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
        testEventList.add(event);
        assertTrue(testEventList.contains(event));
    }

    @Test
    void testDumpSchedule() {
        testEventList.add(event);
        assertEquals("test event on 20 at 2000", accdashboard.dumpSchedule(testEventList));
    }

}
