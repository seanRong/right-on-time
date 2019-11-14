import model.*;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassEventTest {
    private String defaultDateRaw = "31/12/1998";
    Point2D.Double point = new Point2D.Double(2000, 2000);
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);
    ClassSchedule classSchedule = new ClassSchedule();
    ClassEvent classEvent = new ClassEvent("some name", defaultDate, point);


    public ClassEventTest() throws ParseException {
    }

    @Test
    void testSetSchedule() {
        classEvent.setSchedule(classSchedule);
        assertEquals(classSchedule, classEvent.getClassSchedule());
    }
}
