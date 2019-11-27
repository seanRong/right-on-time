package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static model.SaveModule.parseEventJson;

public class EventManager implements Loadable, Saveable {
    private ArrayList<Event> eventList;
    private JSONArray eventJson;
    public ClassSchedule classSchedule;
    public HashMap<String, Event> fastLookup = new HashMap<>();
    private SaveModule saveModule = new SaveModule();

    public EventManager() {
        this.eventList = new ArrayList<>();
        JSONArray eventJson = new JSONArray();
        classSchedule = new ClassSchedule();
        this.eventJson = eventJson;
        load();
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

    public void load() {
        this.eventJson = saveModule.load();
    }

    public void save() {
        saveModule.save(eventJson);
    }

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

    public String dumpSchoolSchedule() {
        System.out.println("current school schedule:");
        return classSchedule.printableSchedule();
    }


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

    public void absurdTimeChecker(Date d) throws AbsurdTimeException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date curr = new Date();
        if (d.compareTo(curr) < 0) {
            throw new AbsurdTimeException("Cannot go back in time");
        }
    }

}
