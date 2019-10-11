import model.Event;

import model.OneTimeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneTimeEventTest extends Event{
    private OneTimeEvent eventTest;

    public OneTimeEventTest(String eventName, SimpleDateFormat eventDate, long eventLocation) {
        super(eventName, eventDate, eventLocation);
    }

    @BeforeEach
    void runBefore() {
        eventTest = new OneTimeEvent("some name", 10, 2000);
    }

    @Test
    void testGetEventName() {
        assertEquals("some name", eventTest.getEventName());
    }

    @Test
    void testGetEventDate() {
        assertEquals(10, eventTest.getEventDate());
    }

    @Test
    void testGetEventLocation() {
        assertEquals(2000, eventTest.getEventLocation());
    }

    @Test
    void testGetEventDetails() {
        assertEquals("some name on 10 at 2000", eventTest.getEventDetails());
    }

    @Test
    void testGetPriority() {
        assertEquals("low", eventTest.getPriority());
    }

    @Test
    void testSetPriority() {
        eventTest.setPriority("high");
        assertEquals( "high", eventTest.getPriority());
    }

    @Test
    void testGetType() {
        assertEquals(eventTest.getEventType(), "one time");
    }
}
