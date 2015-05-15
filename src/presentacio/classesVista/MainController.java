package presentacio.classesVista;

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
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;
    @FXML
    private Label headerLabel;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node, String nomVista) {
        headerLabel.setText(nomVista);
        vistaHolder.getChildren().setAll(node);
    }
}
