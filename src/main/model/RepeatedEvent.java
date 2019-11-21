package model;

import java.awt.geom.Point2D;
import java.util.Date;

public class RepeatedEvent extends Event {
    private String repeatInterval;

    public RepeatedEvent(String eventName, Date eventDate, Point2D.Double eventLocation) {
        super(eventName, eventDate, eventLocation);
        this.repeatInterval = "daily";
    }

    public String setRepeatInterval(String interval) {
        this.repeatInterval = interval;
        return interval;
    }

    public String getRepeatInterval() {
        return this.repeatInterval;
    }

    public String getEventType() {
        return "repeated";
    }
}
