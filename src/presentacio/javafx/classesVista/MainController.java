package presentacio.javafx.classesVista;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/15/15
 */
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Classe Main controller per tot el layout.
 */
public class MainController {

    /** Holder per vistes canviables */
    @FXML
    private StackPane vistaHolder;
    /** Label per mostrar el titol de la vista actual*/
    @FXML
    private Label headerLabel;

    /**
     * Substitueix la vista mostrada a vistaHolder per una nova vista
     * @param node la vista que substiituira la actual a vistaHolder
     * @param nomVista el nom de la vista que sera el nou titol
     */
    public void setVista(Node node, String nomVista) {
        headerLabel.setText(nomVista);
        vistaHolder.getChildren().setAll(node);
    }
}
