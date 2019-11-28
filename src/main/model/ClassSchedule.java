package model;

import java.util.ArrayList;

public class ClassSchedule {
    ArrayList<Event> schedule;

    public ClassSchedule() {
        schedule = new ArrayList<>();
    }

    //MOD: this
    //EFF: adds given class from this schedule
    public void addClass(ClassEvent ce) {
        schedule.add(ce);
    }

    //MOD: this
    //EFF: removes given class from this schedule
    public void removeClass(ClassEvent ce) {
        schedule.remove(ce);
    }

    //EFF: returns size of the schedule
    public int getSize() {
        return schedule.size();
    }

    //REQ: index to be in bounds
    //EFF: returns an event at the given index
    public Event getEvent(int index) {
        return schedule.get(index);
    }

    //EFF: gives you a string that represents the schedule
    public String printableSchedule() {
        String toPrint = "";
        for (Event e: schedule) {
            toPrint += e.getEventDetails();
        }
        return toPrint;
    }
}
