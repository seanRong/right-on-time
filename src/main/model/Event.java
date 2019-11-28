package model;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.Observable;

public abstract class Event extends Observable implements Activity {
    public String name;
    public Date date;
    public Point2D.Double location;

    // Constructs an event
    //MODIFIES: this
    // EFFECTS: event has name eventName, date eventDate, location eventLocation
    public Event(String eventName, Date eventDate, java.awt.geom.Point2D.Double eventLocation) {
        this.name = eventName;
        this.date = eventDate;
        this.location = eventLocation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(Point2D.Double point) {
        this.location = point;
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
    public java.awt.geom.Point2D.Double getEventLocation() {
        return this.location;
    }

    //EFFECTS: returns a string containing the name, date and location of the event
    public String getEventDetails() {
        return this.name + " on " + this.date + " at " + this.location;
    }

    public abstract String getEventType();

    public void notifyUp() {
        setChanged();
        notifyObservers();
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
}