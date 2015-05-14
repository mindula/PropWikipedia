package presentacio.classesVista;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/14/15
 */
public class Launcher {
    private static Launcher INSTANCE;
    private Stage primaryStage;

    public static Launcher getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Launcher();
        return INSTANCE;
    }

    private Launcher() {
        primaryStage = new Stage();
    }

    public void launchVista(String nomVista, String titolVista) throws Exception {
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../vistes/" + nomVista + ".fxml"));
        primaryStage.setTitle(titolVista);
        primaryStage.setScene(new Scene(root, 720, 576));
        primaryStage.show();
    }
}
