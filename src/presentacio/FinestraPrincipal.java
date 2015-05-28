package presentacio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        /*
            Aqui afegim les TABs
         */
        //tabPane.getTabs().add(tab);
        tabPane.getTabs().add(new NavegacioVista());

        StackPane root = new StackPane();
        root.getChildren().add(tabPane);
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
