package presentacio;

import domini.controladors.CtrlWikipedia;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

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
    private HashMap<String, Integer> nomId;

    public ComparacioTemes(Stage stage, FinestraPrincipal nav) {
        parentStage = stage;
        finestraPrincipal = nav;
        nomId = new HashMap<>();
    }

    public Scene getScene() {
        HBox parent = new HBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 900, 700);

        llistaExecucions1 = new ListView<>();
        llistaExecucions2 = new ListView<>();
        actualitzarLlistes();
        HBox liniaLlistes = new HBox(10);
        liniaLlistes.getChildren().addAll(llistaExecucions1, llistaExecucions2);

        Button compareButton = new Button("Comparar");

        Label milisegons = new Label("Temps Total en cercar: ");
        final Label miliNum1 = new Label();
        final Label miliNum2 = new Label();
        Label nombreComunitats = new Label("Nombre de comunitats: ");
        final Label nombreNum1 = new Label();
        final Label nombreNum2 = new Label();
        Label algoritme = new Label("Algoritme utilitzat: ");
        final Label algoNum1 = new Label();
        final Label algoNum2 = new Label();
        Label criteri = new Label("Criteris escullits: ");
        final Label critNum1 = new Label();
        final Label critNum2 = new Label();
        Label mitjana = new Label("Num. de de nodes per comunitat: ");
        final Label mitjNum1 = new Label();
        Label mitjNum2 = new Label();

        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!llistaExecucions1.getSelectionModel().isEmpty() &&
                        !llistaExecucions2.getSelectionModel().isEmpty()) {
                    String exec1 = llistaExecucions1.getSelectionModel().getSelectedItem();
                    String exec2 = llistaExecucions2.getSelectionModel().getSelectedItem();
                    int id1 = nomId.get(exec1);
                    int id2 = nomId.get(exec2);

                    String[] valors1 = CtrlWikipedia.getInstance().getInfoExecucio(id1).split("-");
                    String[] valors2 = CtrlWikipedia.getInstance().getInfoExecucio(id2).split("-");

                    miliNum1.setText(valors1[0]);
                    miliNum2.setText(valors2[0]);
                    nombreNum1.setText(valors1[1]);
                    nombreNum2.setText(valors2[1]);
                    algoNum1.setText(valors1[2]);
                    algoNum2.setText(valors2[2]);
                    critNum1.setText(valors1[3]);
                    critNum2.setText(valors2[3]);
                    mitjNum1.setText(valors1[4]);
                    miliNum2.setText(valors2[4]);
                }
            }
        });

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
            String nom = "Execucio: " + String.valueOf(i);
            llistaExecucions1.getItems().add(nom);
            llistaExecucions2.getItems().add(nom);
            nomId.put(nom, i);
        }
    }
}
