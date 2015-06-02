package presentacio;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 6/1/15
 */
public class AlertDialog extends Stage {

    private static String titol;
    private static String missatge;
    private static Scene scene;

    public AlertDialog(String titolD, String missatgeD){
        titol = titolD;
        missatge = missatgeD;
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UTILITY);
        setWidth(300);
        setHeight(75);
        setResizable(false);
        setTitle(titolD);
        VBox parent = new VBox(10);
        parent.setPadding(new Insets(10));
        parent.setAlignment(Pos.CENTER);
        Label message = new Label(missatgeD);
        Button ok = new Button("D'acord");
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });
        parent.getChildren().addAll(message, ok);
        scene = new Scene(parent);
        setScene(scene);
    }

    public AlertDialog(String titolD, String missatgeD, double width, double height){
        titol = titolD;
        missatge = missatgeD;
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UTILITY);
        setWidth(width);
        setHeight(height);
        setResizable(false);
        setTitle(titolD);
        VBox parent = new VBox(10);
        parent.setPadding(new Insets(10));
        parent.setAlignment(Pos.CENTER);
        Label message = new Label(missatgeD);
        Button ok = new Button("D'acord");
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });
        parent.getChildren().addAll(message, ok);
        scene = new Scene(parent);
        setScene(scene);
    }

    public void mostrarAlertDialog() {
        show();
    }
}
