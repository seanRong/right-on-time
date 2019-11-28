package network;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DistanceList {
    private ArrayList<String> travelTimes;
    private Point2D.Double home;

    public DistanceList(Point2D.Double home) {
        travelTimes = new ArrayList<>();
        this.home = home;
    }

    public ArrayList<String> getTravelTimes() {
        return travelTimes;
    }
}
