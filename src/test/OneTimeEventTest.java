import model.Event;

import model.OneTimeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneTimeEventTest {
    private OneTimeEvent eventTest;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);

    public OneTimeEventTest() throws ParseException {
    }

    @BeforeEach
    void runBefore() {
        eventTest = new OneTimeEvent("some name", defaultDate, 2000);
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
