package ui;

import javafx.event.ActionEvent;

public class Controller {
    private GUI gui;

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

    }
}
