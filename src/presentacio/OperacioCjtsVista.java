package presentacio;

import domini.controladors.CtrlComunitat;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 31/05/15
 */
public class OperacioCjtsVista {

    private final double SPACE = 10;
    private final TemesVista temesVista;
    //private Stage stagePropi;

    private ListView<String> llistaT1;
    private ListView<String> llistaT2;

    public OperacioCjtsVista(TemesVista temesVis) {
        temesVista = temesVis;
        //stagePropi = stage;
    }

    public Scene getScene(){
        HBox parent = new HBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent);

        llistaT1 = new ListView<>();
        llistaT2 = new ListView<>();
        actualitzaTemes();

        VBox boxBotons = new VBox(SPACE);
        boxBotons.setPadding(new Insets(40));
        boxBotons.setAlignment(Pos.CENTER);
        Button unir = new Button("Unir");
        unir.setMaxWidth(Double.MAX_VALUE);
        Separator separator1 = new Separator(); separator1.setVisible(false);
        Button interseccionar = new Button("Interseccionar");
        interseccionar.setMaxWidth(Double.MAX_VALUE);
        Separator separator2 = new Separator(); separator2.setVisible(false);
        Button restar = new Button("Restar");
        restar.setMaxWidth(Double.MAX_VALUE);
        boxBotons.getChildren().addAll(unir, separator1, interseccionar, separator2, restar);

        parent.getChildren().addAll(llistaT1, llistaT2, boxBotons);

        // OnMouseClicked Listeners:

        unir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaT1.getSelectionModel().isEmpty() && !llistaT2.getSelectionModel().isEmpty()) {
                    try {
                        CtrlComunitat.getInstance().unio(
                                llistaT1.getSelectionModel().getSelectedItem(), llistaT2.getSelectionModel().getSelectedItem());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    actualitzaTemes();
                    temesVista.actualitzaTemes();
                } else System.out.println("Selecciona dos conjunts");
            }
        });
        interseccionar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaT1.getSelectionModel().isEmpty() && !llistaT2.getSelectionModel().isEmpty()) {
                    try {
                        CtrlComunitat.getInstance().interseccio(
                                llistaT1.getSelectionModel().getSelectedItem(), llistaT2.getSelectionModel().getSelectedItem());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    actualitzaTemes();
                    temesVista.actualitzaTemes();
                } else System.out.println("Selecciona dos conjunts");
            }
        });
        restar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaT1.getSelectionModel().isEmpty() && !llistaT2.getSelectionModel().isEmpty()) {
                    try {
                        CtrlComunitat.getInstance().diferencia(
                                llistaT1.getSelectionModel().getSelectedItem(), llistaT2.getSelectionModel().getSelectedItem());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    actualitzaTemes();
                    temesVista.actualitzaTemes();
                } else System.out.println("Selecciona dos conjunts");
            }
        });

        return scene;
    }

    public void actualitzaTemes() {
        Collection<String> cjtComunitats
                = CtrlComunitat.getInstance().getConjunt().getNoms();
        llistaT1.getItems().addAll(cjtComunitats);
        llistaT2.getItems().addAll(cjtComunitats);
    }
}
