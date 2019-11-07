import model.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClassScheduleTest {
    ClassSchedule cs = new ClassSchedule();
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("06/20/2099");
    ClassEvent ce = new ClassEvent("CPSC", defaultDate, 1234);

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
