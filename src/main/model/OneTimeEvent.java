package model;

import java.text.SimpleDateFormat;

public class OneTimeEvent extends Event {
    public String priority;

    public OneTimeEvent(String eventName, SimpleDateFormat eventDate, long eventLocation) {
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

    public String getEventDetails() {
        return this.name + " on " + this.date + " at " + this.location + " , is one time";
    }


    public String getEventType() {
        return "one time";
    }
}
