import model.Event;

import model.OneTimeEvent;
import model.RepeatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedEventTest {
    private RepeatedEvent eventTest;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    Point2D.Double point = new Point2D.Double(2000, 2000);


    public RepeatedEventTest() throws ParseException {
    }

    @BeforeEach
    void runBefore() {
        eventTest = new RepeatedEvent("some name", defaultDate, point);
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
    void testGetInterval() {
        assertEquals("daily", eventTest.getRepeatInterval());
    }

    @Test
    void testSetInterval() {
        eventTest.setRepeatInterval("yearly");
        assertEquals( "yearly", eventTest.getRepeatInterval());
    }

    @Test
    void testGetType() {
        assertEquals(eventTest.getEventType(), "repeated");
    }

}
