package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;
import java.text.ParseException;

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

    public void setGui(GUI gui) {
        this.gui = gui;
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
    }

    public void save(ActionEvent actionEvent) {
    }

    public void makeEventOneTime(ActionEvent actionEvent) {
        String name = oneTimeName.getCharacters().toString();
        String date = oneTimeDate.getCharacters().toString();
        String lat = oneTimeLat.getCharacters().toString();
        String lon = oneTimeLong.getCharacters().toString();
//        System.out.println(this.gui.getMakeEventUI());
        try {
            gui.getMakeEventUI().makeOneTimeEvent(name, date, lat, lon);
        } catch (ParseException e) {
            System.out.println("parse failed at controller");
        }
    }
}
