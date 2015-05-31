package presentacio;

import domini.controladors.CtrlWikipedia;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class NavegacioC {

    private final double SPACE = 10;
    private String nomC;
    private final NavegacioVista navegacioVista;
    private Stage stagePropi;

    private ListView<String> llistaP;
    private ListView<String> llistaSuper;
    private ListView<String> llistaSub;
    private ListView<String> llistaTema;

    public NavegacioC(String nomCategoria,  NavegacioVista nav, Stage stage){
        nomC = nomCategoria;
        navegacioVista = nav;
        stagePropi = stage;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 1024, 768); // necessari perque es massa gran)

        Label titol = new Label(nomC);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);

        Label labelP = new Label("Pàgines que conté");
        llistaP = new ListView<>();
        llistaP.getItems().addAll("QWERTTYUIOP", "asdf"); // TODO: necessaries funcions CtrlWikipedia
        Button accedirP = new Button("Accedir a la pàgina");
        accedirP.setMaxWidth(Double.MAX_VALUE);
        VBox boxP = new VBox(SPACE);
        boxP.getChildren().addAll(labelP, llistaP, accedirP);
        Label labelSuper = new Label("Supercategories");
        llistaSuper = new ListView<>();
        llistaSuper.getItems().addAll("super1");
        Button accedirSuper= new Button("Accedir a la supercategoria");
        accedirSuper.setMaxWidth(Double.MAX_VALUE);
        VBox boxSuper = new VBox(SPACE);
        boxSuper.getChildren().addAll(labelSuper, llistaSuper, accedirSuper);
        Label labelSub = new Label("Subcategories");
        llistaSub = new ListView<>();
        llistaSub.getItems().addAll("sub1");
        Button accedirSub = new Button("Accedir a la subcategoria");
        accedirSub.setMaxWidth(Double.MAX_VALUE);
        VBox boxSub = new VBox(SPACE);
        boxSub.getChildren().addAll(labelSub, llistaSub, accedirSub);

        HBox boxPagSuperSub = new HBox(SPACE);
        boxPagSuperSub.setAlignment(Pos.CENTER);
        boxPagSuperSub.getChildren().addAll(boxP, boxSuper, boxSub);

        Button editar = new Button("Editar la categoria");
        Button eliminar = new Button("Eliminar la categoria");
        editar.setMaxWidth(200);
        eliminar.setMaxWidth(200);

        // llista amb cats del mateix tema
        Label labelTema = new Label("Categories del mateix tema");
        llistaTema = new ListView<>();
        llistaTema.getItems().addAll("DelMateixTema");
        Button accedirCatTema = new Button("Accedir a la categoria");
        accedirCatTema.setMaxWidth(Double.MAX_VALUE);
        VBox boxTema = new VBox(SPACE);
        boxTema.getChildren().addAll(labelTema, llistaTema, accedirCatTema);

        ListView<String> mockList1 = new ListView<>(); mockList1.setVisible(false);
        ListView<String> mockList2 = new ListView<>(); mockList2.setVisible(false);
        VBox boxMock1 = new VBox(SPACE);
        boxMock1.getChildren().addAll(mockList1);
        VBox boxMock2 = new VBox(SPACE);
        boxMock2.setAlignment(Pos.BOTTOM_RIGHT);
        boxMock2.getChildren().addAll(mockList2, editar, eliminar);

        HBox boxMateixTema = new HBox(SPACE);
        boxMateixTema.setAlignment(Pos.CENTER);
        boxMateixTema.getChildren().addAll(boxMock1, boxTema, boxMock2);



        parent.getChildren().addAll(titol, separator1, boxPagSuperSub, boxMateixTema);

        // OnMouseClicked Listeners:
        accedirP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirPagina(llistaP);
            }
        });
        accedirSuper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaSuper);
            }
        });
        accedirSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaSub);
            }
        });
        accedirCatTema.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaTema);
            }
        });
        editar.setOnMouseClicked(new EventHandler<MouseEvent>() { // TODO
            @Override
            public void handle(MouseEvent mouseEvent) {
                //
            }
        });
        eliminar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogEliminarCategoria();
            }
        });

        // finalment
        return scene;
    }


    private void dialogEliminarCategoria(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la categoria " + nomC + "?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlWikipedia.getInstance().elimCat(nomC);
                navegacioVista.carregarCategories();
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
        dialog.setTitle("Eliminar categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void accedirCategoria(ListView<String> llista){
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

    private void accedirPagina(ListView<String> llista){
        if (!llista.getSelectionModel().isEmpty()) {
            Stage stage = new Stage();;
            stagePropi.close();
            NavegacioP navegacioP = new NavegacioP(llista.getSelectionModel().getSelectedItem(),
                    navegacioVista, stage);
            Scene scene = navegacioP.getScene();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Pàgina");
            stage.show();
        }
        else System.out.println("No hi ha pag seleccionada");
    }

}
