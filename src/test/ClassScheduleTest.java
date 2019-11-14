import model.*;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClassScheduleTest {
    Point2D.Double point = new Point2D.Double(2000, 2000);
    ClassSchedule cs = new ClassSchedule();
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("06/20/2099");
    ClassEvent ce = new ClassEvent("CPSC", defaultDate, point);

    public ClassScheduleTest() throws ParseException {
    }

    @Test
    void testAdd() {
        cs.addClass(ce);
        assertEquals(ce, cs.getEvent(0));

    }

    @Test
    void testRemove() {
        cs.addClass(ce);
        cs.removeClass(ce);
        assertEquals(0, cs.getSize());
    }

    @Test
    void testPrintable() {
        cs.addClass(ce);
        cs.removeClass(ce);
        assertEquals(ce.getEventDetails(), cs.printableSchedule());
    }
}
