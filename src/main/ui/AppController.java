package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Event;
import network.DistanceCalculator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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
        distanceListView.setItems(odl);
    }
}
