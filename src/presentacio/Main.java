package presentacio;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/14/15
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacio.classesVista.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Launcher.getInstance().launchVista(NomsVistes.MainWindow, "Wikipedia");
    }



    public static void main(String[] args) {
        launch(args);
    }
}

