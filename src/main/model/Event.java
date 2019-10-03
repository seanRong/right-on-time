package model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    public String name;
    public int date;
    public long location; //will be coordinates

    // Constructs an event
    //MODIFIES: this
    // EFFECTS: event has name eventName, date eventDate, location eventLocation
    public Event(String eventName, int eventDate, long eventLocation) {
        this.name = eventName;
        this.date = eventDate;
        this.location = eventLocation;
    }

    //EFFECTS: returns the string of the event's name
    public String getEventName() {
        return this.name;
    }

    //EFFECTS: returns the int of the event's date
    public int getEventDate() {
        return this.date;
    }

    //EFFECTS: returns the long of the event's location
    public long getEventLocation() {
        return this.location;
    }

    //EFFECTS: returns a string containing the name, date and location of the event
    public String getEventDetails() {
        return this.name + " on " + this.date + " at " + this.location;
    }



}
