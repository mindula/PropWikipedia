package presentacio;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/14/15
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentacio.classesVista.*;

import java.io.IOException;


public class Main extends Application {

    private static final String APP_NAME = "Wikipedia";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(createScene(loadMainPane()));
        primaryStage.setTitle(APP_NAME);
        primaryStage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(
                "./vistes/" + NomsVistes.MainController + ".fxml"
        ));

        MainController mainController = loader.getController();

        NavegadorVistes.setMainController(mainController);
        NavegadorVistes.loadVista(NomsVistes.MenuPrincipal);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        scene.getStylesheets().setAll(
                getClass().getResource("./vistes/vista.css").toExternalForm()
        );

        return scene;
    }



    public static void main(String[] args) {
        launch(args);
    }
}

