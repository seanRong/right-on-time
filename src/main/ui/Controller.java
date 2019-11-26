package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {
    private GUI gui;
    @FXML
    private TextField oneTimeName;
    @FXML
    private TextField oneTimeDate;
    @FXML
    private TextField oneTimeLocation;


    public void setGui(GUI gui) {
        this.gui = gui;
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
//        System.out.println(oneTimeDate);
        System.out.println(oneTimeName.getCharacters());
    }
}
