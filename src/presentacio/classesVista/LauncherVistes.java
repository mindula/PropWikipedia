package presentacio.classesVista;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/14/15
 */
public class LauncherVistes {


    public void launchVista(String vista) throws Exception{
        String s;
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vistes/" + vista + ".fxml"));
        primaryStage.setTitle("Wikipedia");
        primaryStage.setScene(new Scene(root, 720, 576));
        primaryStage.show();
    }
}
