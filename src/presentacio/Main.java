package presentacio;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/14/15
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentacio.classesVista.EnumVistes;
import presentacio.classesVista.LauncherVistes;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LauncherVistes lv = new LauncherVistes();
        lv.launchVista(EnumVistes.MainWindow.toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}

