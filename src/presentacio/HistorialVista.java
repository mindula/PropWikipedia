package presentacio;

import domini.controladors.CtrlWikipedia;
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

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 30/05/15
 */

public class HistorialVista {

    private final double SPACE = 10;

    private ListView<String> llistaCerques;

    public HistorialVista(){

        VBox parentBox = new VBox(SPACE);
        parentBox.setPadding(new Insets(20));

        llistaCerques = new ListView<>();
        // TODO: agafar info del CtrlHistorial
        Button eliminarCerca = new Button("Eliminar cerca");

        parentBox.getChildren().addAll(llistaCerques, eliminarCerca);

        // OnMouseClicked Listeners:

        eliminarCerca.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaCerques.getSelectionModel().isEmpty())
                    dialogEliminarCerca();
                else System.out.println("No hi ha cerca seleccionada");
            }
        });

    }

    private void dialogEliminarCerca(){
        final String cerca = llistaCerques.getSelectionModel().getSelectedItem();
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la cerca " + cerca + "?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO: eliminar la cerca cridant a CtrlHistorial i actualitzar HistorialVista
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
