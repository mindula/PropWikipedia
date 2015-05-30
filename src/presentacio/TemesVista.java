package presentacio;

import domini.controladors.CtrlComunitat;
import domini.modeldades.graf.NodeCategoria;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import presentacio.dialog.TemesDialog;
import prop.classescompartides.graf.Comunitat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 28/5/15
 */
public class TemesVista extends Tab {

    private final double SPACE = 10;
    private ListView<String> llistaT;
    private ListView<String> llistaC;


    public TemesVista() {
        setText("Temes");

        llistaT = new ListView<>();
        actualitzaTemes();
        llistaC = new ListView<>();
        llistaC.getItems().addAll("Cat 1", "Cat 2");

        HBox liniaTC = new HBox();
        liniaTC.getChildren().addAll(llistaT, llistaC);

        EventHandler<MouseEvent> action = listenerButtons();
        Button temaNouButton = new Button("Crear tema");
        temaNouButton.setMaxWidth(Double.MAX_VALUE);
        temaNouButton.setOnMouseClicked(action);
        Button reanomenarTemaButton = new Button("Reanomenar tema");
        reanomenarTemaButton.setMaxWidth(Double.MAX_VALUE);
        reanomenarTemaButton.setOnMouseClicked(action);
        Button affegirCatButton = new Button("Afegir categoria");
        affegirCatButton.setMaxWidth(Double.MAX_VALUE);
        affegirCatButton.setOnMouseClicked(action);
        Button eliminarCatButton = new Button("Eliminar categoria");
        eliminarCatButton.setMaxWidth(Double.MAX_VALUE);
        eliminarCatButton.setOnMouseClicked(action);
        Button moureCatButton = new Button("Moure categoria");
        moureCatButton.setMaxWidth(Double.MAX_VALUE);
        moureCatButton.setOnMouseClicked(action);
        Button opCjtsButton = new Button("Operacions entre temes");
        opCjtsButton.setMaxWidth(Double.MAX_VALUE);
        opCjtsButton.setOnMouseClicked(action);

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
        parentBox.setAlignment(Pos.CENTER);
        parentBox.setPadding(new Insets(20));
        setContent(parentBox);
    }

    public void actualitzaTemes() {
        ArrayList<Comunitat<NodeCategoria>> cjtComunitats
                = CtrlComunitat.getInstance().getConjunt().getCjtComunitats().getComunitats();
        llistaT.getItems().clear();
        for (Comunitat<NodeCategoria> c : cjtComunitats) {
            llistaT.getItems().add("Tema " + c.getId());
        }

    }


    private EventHandler<MouseEvent> listenerButtons() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Button button = (Button) event.getSource();
                String buttonName = button.getText();
                if("Crear tema".equals(buttonName)) {
                    //Dialogs.showInputDialog(new Stage(), null, null, "Crear tema");
                }
            }
        };
    }
}
