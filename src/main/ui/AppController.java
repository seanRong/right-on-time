package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Event;
import network.DistanceCalculator;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

public class AppController {
    private GUI gui;
    @FXML
    private TextField oneTimeName;
    @FXML
    private TextField oneTimeDate;
    @FXML
    private TextField oneTimeLat;
    @FXML
    private TextField oneTimeLong;
    @FXML
    private TextField repeatedName;
    @FXML
    private TextField repeatedDate;
    @FXML
    private TextField repeatedLat;
    @FXML
    private TextField repeatedLong;
    @FXML
    private ListView eventListView;
    @FXML
    private ListView distanceListView;
    @FXML
    private TextField searchBox;
    @FXML
    private TextArea adminTextArea;
    @FXML
    private TextField adminInput;
    private Event foundEvent;
    @FXML
    private TextField newDate;
    @FXML
    private TextField newLocation;

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public GUI getGui() {
        return this.gui;
    }

    //EFF: exits program
    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }
    //EFF: calls eventManager's load
    public void load(ActionEvent actionEvent) {
        gui.getEventManager().load();
    }
    //EFF: calls eventManager's save
    public void save(ActionEvent actionEvent) {
        gui.getEventManager().save();
    }

    //MOD: distanceListView
    //EFF: creates a new distance calculator, and calculates distances + populates the listView
    public void recalculate(ActionEvent actionEvent) {
        try {
            DistanceCalculator dc = new DistanceCalculator(gui.getEventManager().getEventJson());
            setDistanceListView(dc.getDistanceList().getTravelTimes());
        } catch (IOException e) {
            System.out.println("Recalculate function: distance calculator failure");
        }
    }

    //MOD: eventmanager, eventListView
    //EFF: creates a new one time event and adds it to the eventmanager and listview.
    public void makeEventOneTime(ActionEvent actionEvent) {
        String name = oneTimeName.getCharacters().toString();
        String date = oneTimeDate.getCharacters().toString();
        String lat = oneTimeLat.getCharacters().toString();
        String lon = oneTimeLong.getCharacters().toString();
        try {
            Event event = gui.getMakeEventUI().makeOneTimeEvent(name, date, lat, lon);
//            JSONObject jsonObject = gui.getEventManager().getSaveModule().createJsonObject(event);
//            gui.getEventManager().getEventJson().add(jsonObject);
            eventListView.getItems().add(event.getEventDetails());
            adminTextArea.setText("made new one-time");

        } catch (ParseException e) {
            System.out.println("parse failed at controller [one-time]");
        }
    }

    //MOD: eventmanager, eventListView
    //EFF: creates a new repeated event and adds it to the eventmanager and listview.
    public void makeEventRepeated(ActionEvent actionEvent) {
        String name = repeatedName.getCharacters().toString();
        String date = repeatedDate.getCharacters().toString();
        String lat = repeatedLat.getCharacters().toString();
        String lon = repeatedLong.getCharacters().toString();
        try {
            Event event = gui.getMakeEventUI().makeEventRepeated(name, date, lat, lon);
//            JSONObject jsonObject = gui.getEventManager().getSaveModule().createJsonObject(event);
//            gui.getEventManager().getEventJson().add(jsonObject);
            eventListView.getItems().add(event.getEventDetails());
            adminTextArea.setText("made new repeated");
        } catch (ParseException e) {
            System.out.println("parse failed at controller [repeated]");
        }

    }

    //MOD: eventListView
    //EFF: updates eventListView with values in eventList
    public void setEventListView(ArrayList<String> eventList) {
        ObservableList<String> observableEventList = FXCollections.observableList(eventList);
        eventListView.setItems(observableEventList);
    }


    //stub
    public void makeEventSchool(ActionEvent actionEvent) {
    }

    //MOD: eventManager, eventListView
    //EFF resets everything to blank
    public void newFile(ActionEvent actionEvent) {
        gui.getEventManager().wipe();
        eventListView.getItems().clear();
    }

    //MOD: distanceListView
    //EFF: updates distanceListView with values in distanceList
    public void setDistanceListView(ArrayList<String> distanceList) {
        ObservableList<String> odl = FXCollections.observableList(distanceList);
//        System.out.println(odl);
        distanceListView.setItems(odl);
    }

    //REQ: fastLookUp to exist
    //EFF: attempts to retrn the event with the same name as the search query
    public void eventSearch(ActionEvent actionEvent) throws ParseException {
        System.out.println(searchBox.getText());
        System.out.println(gui.getEventManager().fastLookup);
        Event foundEvent =  gui.getEditEventUI().editEventByName(searchBox.getText());
        adminTextArea.setText("found:" + "\n" + foundEvent.getEventDetails()
                + "\n" + "edit date or location?");
        this.foundEvent = foundEvent;
    }

    //MOD: various
    //EFF: confirms the action typed into the admin text box
    public void adminConfirm(ActionEvent actionEvent) throws ParseException {
        if (adminInput.getText().equals("delete")) {
            gui.getEditEventUI().deleteEvent(foundEvent);
        } else if (adminInput.getText().equals("date")) {
            gui.getEditEventUI().editEventDate(foundEvent, newDate.getText());
        } else {
            gui.getEditEventUI().editEventLocation(foundEvent, newLocation.getText());
        }
        adminTextArea.setText("deleted");
    }

    //MOD: home of distance calculator
    //EFF: reruns distance calculator to populate with new time values
    public void setHome(ActionEvent actionEvent) throws IOException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New Home");
        dialog.setContentText("Enter new latitude and longitude");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String[] temp =  result.get().split(",");
            Point2D.Double newHome = new Point2D.Double(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
            DistanceCalculator dc = new DistanceCalculator(gui.getEventManager().getEventJson());
            dc.setHome(newHome);
            dc.findDistance();
            setDistanceListView(dc.getDistanceList().getTravelTimes());
        }
        adminTextArea.setText("setted new home");

    }

    //EFF: opens google maps in native browser
    public void googleMaps(ActionEvent actionEvent) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://maps.google.com"));
    }
}
