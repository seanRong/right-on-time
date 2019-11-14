package model;

import java.awt.geom.Point2D;
import java.util.Date;

public class OneTimeEvent extends Event {
    public String priority;

    public OneTimeEvent(String eventName, Date eventDate, Point2D.Double eventLocation) {
        super(eventName, eventDate, eventLocation);
        this.priority = "low";
    }

    public String getPriority() {
        return this.priority;
    }

    public String setPriority(String p) {
        this.priority = p;
        return p;
    }

    public String getEventType() {
        return "one time";
    }
}
