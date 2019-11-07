package ui;

import model.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Dashboard implements EventManipulator {

    private Scanner scanner;
    private EventManager eventManager;
    private String defaultDateRaw = "31/12/1998";
    Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse(defaultDateRaw);

    // Constructs a dashboard
    // MODIFIES: this
    // EFFECTS: makes a new scanner and ArrayList. beings the enterEvents process.
    public Dashboard() throws ParseException, TooBusyException {
        scanner = new Scanner(System.in);
        eventManager = new EventManager();
        modeChoice();
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
    public void resolveChoice(String choice) throws ParseException, TooBusyException {
        switch (choice) {
            case "add":
                enterEvent();
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
    public void enterEvent() throws ParseException {
        System.out.println("Enter 'r' for repeated, 'o' for one-time, 's' for school");
        String choice = scanner.nextLine();
        System.out.println("you selected: " + choice);
        MakeEventUI makeEventUI = new MakeEventUI(this.eventManager);
        if (choice.equals("o")) {
            makeEventUI.makeOneTimeEvent();
        } else if (choice.equals("r")) {
            makeEventUI.makeEventRepeated();
        } else {
            makeEventUI.makeEventSchool();
        }
    }

    public void editMode() {
        EditEventUI editEventUI = new EditEventUI(this.eventManager);
    }

    private void exitProtocol() {
        System.out.println(eventManager.dumpSchedule());
        System.out.println(eventManager.dumpSchoolSchedule());
        eventManager.save();
        System.exit(0);
    }

    // EFFECTS: starts program by making a new dashboard
    public static void main(String[] args) throws ParseException, TooBusyException {
        new Dashboard();
    }
}
