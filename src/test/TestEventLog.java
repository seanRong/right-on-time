import model.Event;

import model.EventLog;
import model.OneTimeEvent;
import model.RepeatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEventLog {
    private EventLog testLog;
    private RepeatedEvent someR;

    @BeforeEach
    void runBefore() {
        EventLog testlog = new EventLog();
        RepeatedEvent someR = new RepeatedEvent("repeated", 10, 1000);
    }

    @Test
    void testInit() {
        assertEquals(0, testLog.countDone());
    }

    @Test
    void testAdd() {
        testLog.addCompleted(someR);
        assertEquals(1, testLog.countDone());
    }

    @Test
    void testGetLog() {
        assertEquals(testLog.eventlog, testLog.getEventlog());
    }

    @Test
    void testClear() {
        testLog.addCompleted(someR);
        testLog.addCompleted(someR);
        testLog.addCompleted(someR);
        testLog.clearEventLog();
        assertEquals(0, testLog.countDone());
    }

}
