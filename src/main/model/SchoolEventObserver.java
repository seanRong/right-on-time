package model;

import java.util.Observable;
import java.util.Observer;

public class SchoolEventObserver implements Observer {
    public void update(Observable obj, Object arg) {
        System.out.println("School observer Notified");
    }
}
