package model;

import java.util.Date;

public class OneTimeEvent extends Event {
    public String priority;

    public OneTimeEvent(String eventName, Date eventDate, long eventLocation) {
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
