package ui;

import model.Event;

import java.util.Scanner;
import java.util.ArrayList;

public class Dashboard {
    private ArrayList<Event> eventList;
    private Scanner scanner;

    public Dashboard() {
        eventList = new ArrayList<>();
        scanner = new Scanner(System.in);
        getEvents();
    }

    public void getEvents() {
        String choice = "";
        while (true) {
            System.out.println("Enter 'add' to add event or 'done' to exit");
            choice = scanner.nextLine();
            System.out.println("you selected: " + choice);

            if (choice.equals("done")) {
                break;
            }

            Event myEvent = makeEvent();
            System.out.println("event added, it's called: " + myEvent.name + " on " + myEvent.date
            + " at " + myEvent.location);
        }

        System.out.println("Your list: " + eventList );
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

//    public String printEvents(Event eventList[]) {
//        for (int i = 0; i < eventList.length; i++) {
//            if(eventList[i] != null) {
//                System.out.println(eventList[i].name);
//                System.out.println(eventList[i].date);
//                System.out.println(eventList[i].location);
//            }
//        }
//    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
