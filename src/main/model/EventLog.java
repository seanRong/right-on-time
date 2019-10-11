package model;

import java.util.ArrayList;

public class EventLog {
    public ArrayList<RepeatedEvent> eventlog;

    public EventLog() {
        eventlog = new ArrayList<>();
    }

    public ArrayList<RepeatedEvent> addCompleted(RepeatedEvent repeated) {
        eventlog.add(repeated);
        return eventlog;
    }

    public ArrayList<RepeatedEvent> getEventlog() {
        return eventlog;
    }

    public void clearEventLog() {
        eventlog.clear();
    }

    public int countDone() {
        return eventlog.size();
    }
}
