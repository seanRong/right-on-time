import model.Event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event eventTest;

    @BeforeEach
    void runBefore() {
        eventTest = new Event("some name", 10, 2000);
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

}
