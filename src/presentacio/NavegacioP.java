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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 29/05/15
 */

public class NavegacioP {

    private final double SPACE = 10;
    private String nomP;
    private final NavegacioVista navegacioVista;
    private Stage stagePropi;

    public NavegacioP(String nomPagina, NavegacioVista nav, Stage stage){
        nomP = nomPagina;
        navegacioVista = nav;
        stagePropi = stage;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent);

        Label titol = new Label(nomP);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);
        Label cats = new Label("Categories a les quals pertany");
        final ListView<String> llista = new ListView<>();
        llista.getItems().addAll("Cat1"); // TODO: necessaria funcio de CtrlWikipedia
        Button accedir = new Button("Accedir a la categoria");
        Separator separator2 = new Separator(); separator2.setVisible(false);
        Button editar = new Button("Editar la pàgina");
        Button eliminar = new Button("Eliminar la pàgina");
        accedir.setMaxWidth(Double.MAX_VALUE);
        editar.setMaxWidth(Double.MAX_VALUE);
        eliminar.setMaxWidth(Double.MAX_VALUE);

        parent.getChildren().addAll(titol, separator1, cats, llista,
                accedir, separator2, editar, eliminar);

        // OnMouseClicked Listeners:
        accedir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!llista.getSelectionModel().isEmpty()) {
                    Stage stage = new Stage();
                    stagePropi.close();
                    NavegacioC navegacioC = new NavegacioC(llista.getSelectionModel().getSelectedItem(),
                            navegacioVista, stage);
                    Scene scene = navegacioC.getScene();
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.setTitle("Categoria");
                    stage.show();
                } else System.out.println("No hi ha cat seleccionada");
            }
        });
        editar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO
            }
        });
        eliminar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogEliminarPagina();
            }
        });

        // finalment
        return scene;
    }

    private void dialogEliminarPagina(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la pàgina " + nomP + "?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlWikipedia.getInstance().elimPag(nomP);
                navegacioVista.carregarPagines();
                dialog.close();
                stagePropi.close();
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
        dialog.setTitle("Eliminar pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
