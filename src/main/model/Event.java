package model;

import java.util.Date;

public class Event {
    public String name;
    public int date;
    public long location; //will be coordinates

    public Event(String eventName, int eventDate, long eventLocation) {
        name = eventName;
        date = eventDate;
        location = eventLocation;
    }



}
