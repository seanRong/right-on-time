package ui;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.omg.CosNaming.BindingIterator;

import java.text.ParseException;

public class GUI extends Application {
    private EventManager eventManager;

    public GUI() throws ParseException {
        eventManager = new EventManager();
        MakeEventUI makeEventUI = new MakeEventUI(eventManager);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        primaryStage.setTitle("Right On Time");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws ParseException {
        launch(args);
        new GUI();
    }
}
