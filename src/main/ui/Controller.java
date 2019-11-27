package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Controller {
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


    public void setGui(GUI gui) {
        this.gui = gui;
//        gui.setController(this);
    }

    public GUI getGui() {
        return this.gui;
    }

    public void receiveInput(ActionEvent actionEvent) {
        System.out.println("pressed");
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

    public void makeEventOneTime(ActionEvent actionEvent) {
        String name = oneTimeName.getCharacters().toString();
        String date = oneTimeDate.getCharacters().toString();
        String lat = oneTimeLat.getCharacters().toString();
        String lon = oneTimeLong.getCharacters().toString();
        try {
            gui.getMakeEventUI().makeOneTimeEvent(name, date, lat, lon);
        } catch (ParseException e) {
            System.out.println("parse failed at controller");
        }
    }

    public void setEventListView(ArrayList<model.Event> eventList) {
        ObservableList<model.Event> observableEventList = FXCollections.observableList(eventList);
        eventListView.setItems(observableEventList);
        System.out.println(observableEventList);
    }
}
