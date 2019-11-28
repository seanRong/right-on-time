package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import model.Event;
import network.DistanceCalculator;

import java.awt.geom.Point2D;
import java.io.IOException;
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
//        gui.setController(this);
    }

    public GUI getGui() {
        return this.gui;
    }

    public void masterInput(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void load(ActionEvent actionEvent) {
        gui.getEventManager().load();
    }

    public void save(ActionEvent actionEvent) {
        gui.getEventManager().save();
    }

    public void recalculate(ActionEvent actionEvent) {
        try {
            DistanceCalculator dc = new DistanceCalculator(gui.getEventManager().getEventJson());
            setDistanceListView(dc.getDistanceList().getTravelTimes());
        } catch (IOException e) {
            System.out.println("Recalculate function: distance calculator failure");
        }
    }

    public void makeEventOneTime(ActionEvent actionEvent) {
        String name = oneTimeName.getCharacters().toString();
        String date = oneTimeDate.getCharacters().toString();
        String lat = oneTimeLat.getCharacters().toString();
        String lon = oneTimeLong.getCharacters().toString();
        try {
            Event event = gui.getMakeEventUI().makeOneTimeEvent(name, date, lat, lon);
            eventListView.getItems().add(event.getEventDetails());
        } catch (ParseException e) {
            System.out.println("parse failed at controller");
        }
    }

    public void setEventListView(ArrayList<String> eventList) {
        ObservableList<String> observableEventList = FXCollections.observableList(eventList);
        eventListView.setItems(observableEventList);
    }

    public void makeEventRepeated(ActionEvent actionEvent) {
    }

    public void makeEventSchool(ActionEvent actionEvent) {
    }

    public void newFile(ActionEvent actionEvent) {
        gui.getEventManager().wipe();
        eventListView.getItems().clear();
    }

    public void setDistanceListView(ArrayList<String> distanceList) {
        ObservableList<String> odl = FXCollections.observableList(distanceList);
//        System.out.println(odl);
        distanceListView.setItems(odl);
    }

    public void eventSearch(ActionEvent actionEvent) throws ParseException {
        System.out.println(searchBox.getText());
        System.out.println(gui.getEventManager().fastLookup);
        Event foundEvent =  gui.getEditEventUI().editEventByName(searchBox.getText());
        adminTextArea.setText("found:" + "\n" + foundEvent.getEventDetails()
                + "\n" + "edit date or location?");
        this.foundEvent = foundEvent;
    }

    public void adminInput(ActionEvent actionEvent) throws ParseException {
        if (adminTextArea.getText().equals("delete")) {
            gui.getEditEventUI().deleteEvent(foundEvent);
        } else if (adminTextArea.getText().equals("date")) {
            gui.getEditEventUI().editEventDate(foundEvent, newDate.getText());
        } else {
            gui.getEditEventUI().editEventLocation(foundEvent, newLocation.getText());
        }
    }

    public void setHome(ActionEvent actionEvent) throws IOException {
        TextInputDialog dialog = new TextInputDialog("walter");
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

    }
}
