package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event implements EventInterface {
    public String name;
    public Date date;
    public long location; //will be coordinates

    // Constructs an event
    //MODIFIES: this
    // EFFECTS: event has name eventName, date eventDate, location eventLocation
    public Event(String eventName, Date eventDate, long eventLocation) {
        this.name = eventName;
        this.date = eventDate;
        this.location = eventLocation;
    }

    //EFFECTS: returns the string of the event's name
    public String getEventName() {
        return this.name;
    }

    //EFFECTS: returns the int of the event's date
    public Date getEventDate() {
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

    public String getEventType() {
        return "no type specified";
    }


    //some cred to GeeksForGeeks.org
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        Event event = (Event) obj;
        //no date equality though
        return (event.name.equals(this.name) && event.location == this.location);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
