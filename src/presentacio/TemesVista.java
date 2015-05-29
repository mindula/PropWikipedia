package presentacio;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 28/5/15
 */
public class TemesVista extends Tab {

    private final double SPACE = 10;

    public TemesVista() {
        setText("Temes");

        ListView<String> llistaT = new ListView<>();
        llistaT.getItems().addAll("Tema 1", "Tema 2");
        ListView<String> llistaC = new ListView<>();
        llistaC.getItems().addAll("Cat 1", "Cat 2");

        HBox liniaTC = new HBox();
        liniaTC.getChildren().addAll(llistaT, llistaC);

        Button temaNouButton = new Button("Crear tema nou");
        temaNouButton.setMaxWidth(Double.MAX_VALUE);
        Button reanomenarTemaButton = new Button("Reanomenar tema");
        reanomenarTemaButton.setMaxWidth(Double.MAX_VALUE);
        Button affegirCatButton = new Button("Afegir categoria");
        affegirCatButton.setMaxWidth(Double.MAX_VALUE);
        Button eliminarCatButton = new Button("Eliminar categoria");
        eliminarCatButton.setMaxWidth(Double.MAX_VALUE);
        Button moureCatButton = new Button("Moure categoria");
        moureCatButton.setMaxWidth(Double.MAX_VALUE);
        Button opCjtsButton = new Button("Operacions entre temes");
        opCjtsButton.setMaxWidth(Double.MAX_VALUE);

        VBox liniaBotons = new VBox(50);
        liniaBotons.getChildren().addAll(
                temaNouButton,
                reanomenarTemaButton,
                affegirCatButton,
                eliminarCatButton,
                moureCatButton,
                opCjtsButton);
        liniaBotons.setAlignment(Pos.CENTER_LEFT);

        VBox wrapperTaules = new VBox();
        wrapperTaules.getChildren().addAll(liniaTC);
        wrapperTaules.setAlignment(Pos.CENTER_LEFT);

        HBox parentBox = new HBox(SPACE);
        parentBox.getChildren().addAll(wrapperTaules, liniaBotons);
        parentBox.setAlignment(Pos.CENTER_LEFT);
        parentBox.setPadding(new Insets(20));
        setContent(parentBox);
    }
}
