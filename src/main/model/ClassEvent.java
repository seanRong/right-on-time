package model;

import java.util.Date;

public class ClassEvent extends RepeatedEvent {
    private ClassSchedule cs;

    public ClassEvent(String eventName, Date eventDate, long eventLocation) {
        super(eventName, eventDate, eventLocation);
    }

    public void setSchedule(ClassSchedule cs) {
        if (this.cs != null)
            this.cs.removeClass(this);
        this.cs = cs;
        this.cs.addClass(this);
    }

}
