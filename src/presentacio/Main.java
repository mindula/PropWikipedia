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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacio.classesVista.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Launcher.getInstance().launchVista(NomsVistes.MainWindow, "Wikipedia");
        primaryStage.setScene(
                createScene(
                        loadMainPane()
                )
        );
        primaryStage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream("./vistes/"+NomsVistes.MainController+".fxml"));

        MainController mainController = loader.getController();

        VistaNavigator.setMainController(mainController);
        VistaNavigator.loadVista(NomsVistes.MainWindow);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        /*scene.getStylesheets().setAll(
                getClass().getResource("./vistes/vista.css").toExternalForm()
        );*/

        return scene;
    }



    public static void main(String[] args) {
        launch(args);
    }
}

