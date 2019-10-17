package model;

import java.util.Date;

public interface EventInterface {
    String getEventName();

    Date getEventDate();

    long getEventLocation();

    String getEventDetails();
}
