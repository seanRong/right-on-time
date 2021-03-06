package ui;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.ParseException;

public class GUI extends Application {
    private EventManager eventManager;
    private MakeEventUI makeEventUI;
    private EditEventUI editEventUI;

    public GUI() throws ParseException {
        eventManager = new EventManager();
        makeEventUI = new MakeEventUI(eventManager);
        editEventUI = new EditEventUI(eventManager);
    }

    //EFF: creates the GUI for the application. sets up controllers and the primary stage
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
        Parent root = loader.load();
        AppController controller = loader.getController();
        controller.setGui(this);
        controller.setEventListView(eventManager.getReadableEventList());
        primaryStage.setTitle("Right On Time");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public EventManager getEventManager() {
        return eventManager;
    }

    public MakeEventUI getMakeEventUI() {
        return makeEventUI;
    }

    public EditEventUI getEditEventUI() {
        return this.editEventUI;
    }

    //EFF: starts app by launching a new GUI
    public static void main(String[] args) throws ParseException {
        launch(args);
        new GUI();
    }

}
