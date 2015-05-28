package presentacio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 28/5/15
 */
public class FinestraPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Wikipedia");

        StackPane root = new StackPane();
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
