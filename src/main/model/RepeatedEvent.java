package model;

import java.text.SimpleDateFormat;

public class RepeatedEvent extends Event {
    public String repeatInterval;

    public RepeatedEvent(String eventName, SimpleDateFormat eventDate, long eventLocation) {
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

    public String getEventDetails() {
        return this.name + " on " + this.date + " at " + this.location + " , is repeated";
    }

    public String getEventType() {
        return "repeated";
    }
}
