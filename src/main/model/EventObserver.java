package model;

import java.util.Observable;
import java.util.Observer;

public class EventObserver implements Observer {
    public void update(Observable obj, Object arg) {
        System.out.println("Event observer Notified");
    }
}
