package ui;

import model.*;
import network.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Dashboard {

    private Scanner scanner;
    private EventManager eventManager;

    // Constructs a dashboard
    // MODIFIES: this
    // EFFECTS: makes a new scanner and ArrayList. beings the enterEvents process.
    public Dashboard() throws ParseException, TooBusyException, IOException, org.json.simple.parser.ParseException {
        scanner = new Scanner(System.in);
        eventManager = new EventManager();
//        modeChoice();
        System.out.println("enter calc to go into calculation mode, else will resume normal");
        String choice = scanner.nextLine();
        if (choice.equals("calc") || choice.equals("calculate")) {
            calculateMode();
        } else {
            modeChoice();
        }
    }

    // Logic for adding events
    // EFFECTS: infinite loop that takes user input and passes input to resolver
    public void modeChoice() throws ParseException, TooBusyException {
        System.out.println("Enter 'add' to add event, 'edit' to edit existing event, or 'done' to exit");
        String choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        resolveChoice(choice);
    }

    // routes user based on choice string
    // EFFECTS: may print statements to sout, otherwise leads user to next method
    private void resolveChoice(String choice) throws ParseException, TooBusyException {
        switch (choice) {
            case "add":
//                enterEvent();
                modeChoice();
                break;
            case "done":
                exitProtocol();
                break;
            case "edit":
                editMode();
                modeChoice();
                break;
            default:
                System.out.println("unrecognized input, try again");
                modeChoice();
        }
    }

    // adds a singular event to the ArrayList
    // REQUIRES: eventList already constructed
    // MODIFIES: existing evenList
    // EFFECTS: shows the user what they just added
//    public void enterEvent() throws ParseException {
//        System.out.println("Enter 'r' for repeated, 'o' for one-time, 's' for school");
//        String choice = scanner.nextLine();
//        System.out.println("you selected: " + choice);
//        MakeEventUI makeEventUI = new MakeEventUI(this.eventManager);
//        if (choice.equals("o")) {
//            makeEventUI.makeOneTimeEvent();
//        } else if (choice.equals("r")) {
//            makeEventUI.makeEventRepeated();
//        } else {
//            makeEventUI.makeEventSchool();
//        }
//    }

    //makes a new editEventUI and puts you into editMode
    public void editMode() throws ParseException {
        EditEventUI editEventUI = new EditEventUI(this.eventManager);
    }

    private void exitProtocol() {
        System.out.println(eventManager.dumpSchedule());
        System.out.println(eventManager.dumpSchoolSchedule());
        eventManager.save();
        System.exit(0);
    }

    private void calculateMode() throws IOException, org.json.simple.parser.ParseException {
        new DistanceCalculator(eventManager.getEventJson());
        System.exit(0);
    }

    // EFFECTS: starts program by making a new dashboard
    public static void main(String[] args) throws ParseException, TooBusyException,
            IOException, org.json.simple.parser.ParseException {
        new Dashboard();
    }
}
