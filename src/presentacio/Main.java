package presentacio;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/14/15
 */

import javafx.application.Application;
import javafx.stage.Stage;
import presentacio.classesVista.NomsVistes;
import presentacio.classesVista.LauncherVistes;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LauncherVistes lv = new LauncherVistes();
        lv.launchVista(NomsVistes.MainWindow);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

