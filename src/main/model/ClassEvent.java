package model;

import java.awt.geom.Point2D;
import java.util.Date;

public class ClassEvent extends RepeatedEvent {
    private ClassSchedule cs;

    public ClassEvent(String eventName, Date eventDate, Point2D.Double eventLocation) {
        super(eventName, eventDate, eventLocation);
    }

    public ClassSchedule getClassSchedule() {
        return this.cs;
    }

    //REQ: cs already instantiated somewhere
    //MOD: the class schedule passed in as param
    public void setSchedule(ClassSchedule cs) {
        if (this.cs != null) {
            this.cs.removeClass(this);
        }
        this.cs = cs;
        this.cs.addClass(this);
    }

}
