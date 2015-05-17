package presentacio.classesVista;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/15/15
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;

/**
 * Classe que controla la navegacio entre vistes
 *
 * Tots els metodes de la classe son estatics per facilitar
 * un acces simple des de tota l'aplicacio
 */
public class NavegadorVistes {

    /** EL layout principal de l'aplicacio */
    private static MainController mainController;

    /**
     * Guarda el controlador principal per utilitzarlo posteriorment en tasques de navegacio
     *
     * @param mainController el layout principal de l'aplicacio
     */
    public static void setMainController(MainController mainController) {
        NavegadorVistes.mainController = mainController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml el fitxer fxml a ser carregat
     */
    public static void loadVista(String fxml) {
        try {
            mainController.setVista((Node) FXMLLoader.load(NavegadorVistes.class.getResource("../vistes/"+fxml+".fxml")),
                    fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
