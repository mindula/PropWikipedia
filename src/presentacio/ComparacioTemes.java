package presentacio;

import domini.controladors.CtrlWikipedia;
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
        HBox parent = new HBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 800, 600);

        llistaExecucions1 = new ListView<>();
        llistaExecucions2 = new ListView<>();
        actualitzarLlistes();
        HBox liniaLlistes = new HBox(10);
        liniaLlistes.getChildren().addAll(llistaExecucions1, llistaExecucions2);

        Button compareButton = new Button("Comparar");

        Label milisegons = new Label("Temps en generar: ");
        Label miliNum1 = new Label();
        Label miliNum2 = new Label();
        Label nombreComunitats = new Label("Nombre de comunitats: ");
        Label nombreNum1 = new Label();
        Label nombreNum2 = new Label();
        Label algoritme = new Label("Algoritme utilitzat: ");
        Label algoNum1 = new Label();
        Label algoNum2 = new Label();
        Label criteri = new Label("Criteris escullits: ");
        Label critNum1 = new Label();
        Label critNum2 = new Label();
        Label mitjana = new Label("Nomde de nodes per comunitat: ");
        Label mitjNum1 = new Label();
        Label mitjNum2 = new Label();

        VBox dades1 = new VBox(10);
        dades1.getChildren().addAll(milisegons, miliNum1, miliNum2, nombreComunitats,
                nombreNum1, nombreNum2, algoritme, algoNum1, algoNum2,
                criteri, critNum1, critNum2, mitjana, mitjNum1, mitjNum2);

        VBox comparacio = new VBox(10);
        comparacio.getChildren().addAll(compareButton, dades1);

        parent.getChildren().addAll(liniaLlistes, comparacio);

        return scene;
    }

    private void actualitzarLlistes() {
        int nExecucions = CtrlWikipedia.getInstance().getNombreExecucions();
        for (int i = 0; i < nExecucions; ++i) {
            llistaExecucions1.getItems().add("Execucio " + i);
            llistaExecucions2.getItems().add("Execucio " + i);
        }
    }
}
