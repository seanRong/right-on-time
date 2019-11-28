package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static model.SaveModule.parseEventJson;

public class EventManager implements Loadable, Saveable {
    private ArrayList<Event> eventList;
    private ArrayList<String> readableEventList;
    private JSONArray eventJson;
    public ClassSchedule classSchedule;
    public HashMap<String, Event> fastLookup = new HashMap<>();
    private SaveModule saveModule = new SaveModule();

    public EventManager() {
        this.eventList = new ArrayList<>();
        eventJson = new JSONArray();
        classSchedule = new ClassSchedule();
        load();
        this.readableEventList = printEventList(this.eventJson);
    }

    //MOD: eventList and evenJson of this
    //EFF: re-initializes both, using previous save file
    public void load() {
        this.eventJson = saveModule.load();
        this.eventList = saveModule.loadEventList(eventJson);
    }

    //MOD: save text file
    //EFF: writes current eventJson to the text file
    public void save() {
        saveModule.save(eventJson);
    }

    //REQ: eventList, eventJson, fastLookUp to exist
    //MOD: eventList, eventJson, fastLookUp to exist
    public void addEvent(Event newEvent) {
        try {
            dupeCheck(newEvent);
            absurdTimeChecker(newEvent.getEventDate());
            eventList.add(newEvent);
            JSONObject eventObject = saveModule.createJsonObject(newEvent);
            eventJson.add(eventObject);
            fastLookup.put(newEvent.name, newEvent);
        } catch (TooBusyException e) {
            System.out.println(e);
        } catch (AbsurdTimeException e) {
            System.out.println(e);
        } finally {
            System.out.println("exiting add protocol");
        }
    }

    //EFF: returns eventJson as an array of parsed Strings
    public static ArrayList<String> printEventList(JSONArray eventJson) {
        ArrayList<String> res = new ArrayList<>();
        eventJson.forEach(event -> res.add(parseEventJson((JSONObject) event)));
        return res;
    }

    public ArrayList<String> getReadableEventList() {
        return readableEventList;
    }

    //EFFECTS: prints the entire eventList, even if it's empty.
    public String dumpSchedule() {
        System.out.println("Your schedule: ");
        eventJson.forEach(event -> System.out.println(parseEventJson((JSONObject) event)));

        System.out.println("new entries this session: ");
        StringBuilder toPrint = new StringBuilder();

        for (Event event : eventList) {
            toPrint.append(event.getEventDetails());
        }

        return toPrint.toString();

    }

    //EFF: prints schoolSchedule as a String
    public String dumpSchoolSchedule() {
        System.out.println("current school schedule:");
        return classSchedule.printableSchedule();
    }

    //EFF: if event dates coincide, throws TooBusyException
    public void dupeCheck(Event newEvent) throws TooBusyException {
        boolean dupe = false;
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getEventDate().equals(newEvent.getEventDate())) {
                dupe = true;
            }
        }
        if (dupe) {
            throw new TooBusyException("Too many events on one day");
        }
    }

    //EFF: if event dates would make you go back in time, throws AbsurdTimeException
    public void absurdTimeChecker(Date d) throws AbsurdTimeException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date curr = new Date();
        if (d.compareTo(curr) < 0) {
            throw new AbsurdTimeException("Cannot go back in time");
        }
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public ClassSchedule getClassSchedule() {
        return this.classSchedule;
    }

    public JSONArray getEventJson() {
        return this.eventJson;
    }

    public SaveModule getSaveModule() {
        return this.saveModule;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    //MOD: eventList and evenJson of this
    //EFF: re-initializes both, essentially wiping them of all data.
    public void wipe() {
        this.eventList = new ArrayList<>();
        this.eventJson = new JSONArray();
    }

}
