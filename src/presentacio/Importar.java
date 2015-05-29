package presentacio;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/29/15
 */
public class Importar extends Tab {

    private final double SPACE = 10;

    public Importar() {
        setText("Importar");

        TextField pathImportar = new TextField();
        Button examinarButton = new Button("Examinar");
        HBox liniaImportar = new HBox(10);
        liniaImportar.getChildren().addAll(pathImportar, examinarButton);
        liniaImportar.setAlignment(Pos.BOTTOM_CENTER);

        Button importarButton = new Button("Importar dades");
        importarButton.setAlignment(Pos.TOP_RIGHT);

        VBox parentBox = new VBox();
        parentBox.getChildren().addAll(liniaImportar, importarButton);

        setContent(parentBox);
    }
}
