package presentacio;

import domini.controladors.Historial;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prop.classescompartides.utils.Pair;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 30/05/15
 */

public class HistorialVista {

    private final double SPACE = 10;

    private ListView<String> llistaCerques;

    public HistorialVista(){
    }

    public Scene getScene(){
        VBox parentBox = new VBox(SPACE);
        parentBox.setPadding(new Insets(20));
        Scene scene = new Scene(parentBox);
        llistaCerques = new ListView<>();
        ArrayList<Pair<String, String>> dades = Historial.getInstance().getCerquesStrings();
        ArrayList<String> dadesFormatCorrecte = new ArrayList<>();
        for(Pair<String,String> entrada : dades)
            dadesFormatCorrecte.add("Cerca: " + entrada.getFirst() + " Data: " + entrada.getSecond());
        llistaCerques.getItems().addAll(dadesFormatCorrecte);
        Button eliminarCerca = new Button("Eliminar cerques");
        eliminarCerca.setMaxWidth(Double.MAX_VALUE);
        parentBox.getChildren().addAll(llistaCerques, eliminarCerca);

        // OnMouseClicked Listeners:

        eliminarCerca.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogEliminarCerca();
            }
        });

        return scene;
    }

    private void dialogEliminarCerca(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar totes les cerques?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Historial.getInstance().netejarHistorial();
                llistaCerques.getItems().clear();
                dialog.close();
            }
        });
        Button cancel = new Button("Cancel·lar");
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialog.close();
            }
        });
        botons.getChildren().addAll(ok, cancel);
        botons.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(confirmation, separator, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Eliminar cerca");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
