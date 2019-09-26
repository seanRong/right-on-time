package model;

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

    public void editEvent(String eventName, int eventDate, long eventLocation) {

    }

    //EFFECTS: returns a string containing the name, date and location of the event
    public String getEventDetails() {
        return this.name + " on " + this.date + " at " + this.location;
//        System.out.println(this.name);
//        System.out.println(this.date);
//        System.out.println(this.location);
    }



}
