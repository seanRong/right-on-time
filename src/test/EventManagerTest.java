import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
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
    private RepeatedEvent thirdEvent;
    private String defaultDateRaw = "31/12/2098";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    private String secondDateRaw = "13/12/2020";
    Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(secondDateRaw);
    private String thirdDateRaw = "13/12/1998";
    Date thirdDate = new SimpleDateFormat("dd/MM/yyyy").parse(thirdDateRaw);
    Point2D.Double point = new Point2D.Double(2000, 2000);
    Point2D.Double pointTwo = new Point2D.Double(4500, 4500);


    public EventManagerTest() throws ParseException {
    }

    @BeforeEach
    void runBefore() {
        eventManagerTest = new EventManager();
        testEventList = new ArrayList<Event>();
        event = new OneTimeEvent("test event", defaultDate, point);
        anotherEvent = new OneTimeEvent("second event", secondDate, pointTwo);
        thirdEvent = new RepeatedEvent("third event", thirdDate, point);
        ClassSchedule classSchedule = new ClassSchedule();
        SchoolEvent schoolEvent = new SchoolEvent("school", defaultDate, pointTwo);
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
        assertEquals("test event on 20 at 2000", eventManagerTest.dumpSchedule());
    }

    @Test
    void testDumpSchool() {
        SchoolEvent schoolEvent = new SchoolEvent("school", defaultDate, pointTwo);
        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.addClass(schoolEvent);
        eventManagerTest.getClassSchedule().addClass(schoolEvent);
        assertEquals(classSchedule.printableSchedule(), eventManagerTest.dumpSchoolSchedule());
    }

    @Test
    void testSave() throws TooBusyException {
        eventManagerTest.addEvent(anotherEvent);
        eventManagerTest.save();
        eventManagerTest.load();
        assertTrue(eventManagerTest.getEventList().contains(anotherEvent));
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

    @Test
    void testAbsurdTimeException() {
        try {
            eventManagerTest.absurdTimeChecker(anotherEvent.getEventDate());
        } catch (AbsurdTimeException e) {
            fail("don't exception here");
        }

        try {
            eventManagerTest.absurdTimeChecker(thirdEvent.getEventDate());
            fail("exception missed");
        } catch (AbsurdTimeException e) {
        }
    }


}
