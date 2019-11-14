package model;

import java.awt.geom.Point2D;
import java.util.Date;

public interface Activity {
    String getEventName();

    Date getEventDate();

    Point2D.Double getEventLocation();

    String getEventDetails();
}
