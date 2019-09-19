package ui;

import model.Event;
import model.EventLog;

import java.util.Scanner;
import java.util.ArrayList;

public class Dashboard {
    private ArrayList<Event> eventList;
    private Scanner scanner;

    public Dashboard() {
        eventList = new ArrayList<Event>();
        scanner = new Scanner(System.in);
        getEvents();
    }

    public void getEvents() {
        String choice = "";
        while (true) {
            System.out.println("Enter 'add' to add event or 'done' to exit");
            choice = scanner.nextLine();
            System.out.println("you selected: " + choice);

            if (!(choice.equals("done") || choice.equals("add"))) {
                System.out.println("unrecognized input");
                continue;
            }

            if (choice.equals("done")) {
                break;
            }

            Event myEvent = makeEvent();
            System.out.println("event added, it's called: ");
            System.out.println(myEvent.name + " on " + myEvent.date + " at " + myEvent.location);
        }
        System.out.println("Your list: ");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println(eventList.get(i).date);
            System.out.println(eventList.get(i).location);
            System.out.println(eventList.get(i).name);
        }
    }

    public Event makeEvent() {
        Event myEvent = new Event("",0,0);
        System.out.println("Please enter event title");
        String title = scanner.nextLine();
        myEvent.name = title;
        System.out.println("Please enter event date");
        int date = scanner.nextInt();
        myEvent.date = date;
        System.out.println("Please enter event location");
        int location = scanner.nextInt();
        myEvent.location = location;
        scanner.nextLine();

        addEvent(myEvent);

        return myEvent;
    }

    private void addEvent(Event myEvent) {
        eventList.add(myEvent);
    }

//    private void logEvent(EventLog eventLog, String name, int date, int location) {
//        EventLog.addName(name);
//        EventLog.addDate(date);
//        EventLog.addLocation(location);
//        eventList.add(eventLog);
//    }


    public static void main(String[] args) {
        new Dashboard();
    }
}
