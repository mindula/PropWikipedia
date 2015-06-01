package presentacio;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 1/6/15
 */
public class ComparacioTemes {

    private final double SPACE = 10;
    private Stage parentStage;
    private FinestraPrincipal finestraPrincipal;
    private ListView<String> llistaExecucions1;
    private ListView<String> llistaExecucions2;

    public ComparacioTemes(Stage stage, FinestraPrincipal nav) {
        parentStage = stage;
        finestraPrincipal = nav;
    }

    public Scene getScene() {
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 800, 600);

        llistaExecucions1 = new ListView<>();
        llistaExecucions2 = new ListView<>();
        HBox liniaLlistes = new HBox(10);
        liniaLlistes.getChildren().addAll(llistaExecucions1, llistaExecucions2);

        Button compareButton = new Button("Comparar");

        Label milisegons = new Label("Temps en generar: ");
        Label nombreComunitats = new Label("Nomde de comunitats: ");


        HBox parentBox = new HBox(10);

        return scene;
    }
}
