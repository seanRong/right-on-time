package model;

import java.util.Date;

public class RepeatedEvent extends Event {
    public String repeatInterval;

    public RepeatedEvent(String eventName, Date eventDate, long eventLocation) {
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
