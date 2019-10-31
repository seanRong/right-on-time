package model;

import java.util.ArrayList;

public class ClassSchedule {
    ArrayList<Event> schedule;

    public ClassSchedule() {
        schedule = new ArrayList<>();
    }

    public void addClass(ClassEvent ce) {
        schedule.add(ce);
    }

    public void removeClass(ClassEvent ce) {
        schedule.remove(ce);
    }

    public int getSize() {
        return schedule.size();
    }

    public Event getEvent(int index) {
        return schedule.get(index);
    }
}
