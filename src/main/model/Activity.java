package model;

import java.util.Date;

public interface Activity {
    String getEventName();

    Date getEventDate();

    long getEventLocation();

    String getEventDetails();
}
