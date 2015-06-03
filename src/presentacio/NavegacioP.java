package presentacio;

import domini.controladors.CtrlCatPag;
import domini.controladors.CtrlWikipedia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentacio.autocompletat.AutoCompleteComboBoxListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    private ListView<String> llista;
    private Label titol;

    public NavegacioP(String nomPagina, NavegacioVista nav, Stage stage){
        nomP = nomPagina;
        navegacioVista = nav;
        stagePropi = stage;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 375, 700);

        titol = new Label(nomP);
        titol.setFont(new Font(25));
        Separator separator1 = new Separator(); separator1.setVisible(false);
        Label cats = new Label("Categories a les quals pertany");
        llista = new ListView<>();
        llista.getItems().addAll(CtrlCatPag.getInstance().getCategoriesPagina(nomP));
        Button accedir = new Button("Accedir a la categoria");
        Button eliminarCat = new Button("Eliminar categoria de la pàgina");
        Separator separator2 = new Separator(); separator2.setVisible(false);
        Button afegirCat = new Button("Afegir categoria a la pàgina");
        Button reanomenarPag = new Button("Modificar el nom de la pàgina");
        Button veureNavegador = new Button("Visitar web");
        Button eliminarPag = new Button("Eliminar la pàgina");
        accedir.setMaxWidth(Double.MAX_VALUE);
        eliminarCat.setMaxWidth(Double.MAX_VALUE);
        afegirCat.setMaxWidth(Double.MAX_VALUE);
        reanomenarPag.setMaxWidth(Double.MAX_VALUE);
        veureNavegador.setMaxWidth(Double.MAX_VALUE);
        eliminarPag.setMaxWidth(Double.MAX_VALUE);

        parent.getChildren().addAll(titol, separator1, cats, llista,
                accedir, eliminarCat, separator2, afegirCat, reanomenarPag, veureNavegador, eliminarPag);

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
                } else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap categoria seleccionada");
                    alertDialog.show();
                }
            }
        });
        eliminarCat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llista.getSelectionModel().isEmpty())
                    dialogEliminarCatDeLaPagina();
                else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap categoria seleccionada");
                    alertDialog.show();
                }
            }
        });
        afegirCat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogAfegirCat();
            }
        });
        reanomenarPag.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogReanomenarPag();
            }
        });
        veureNavegador.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String url = CtrlCatPag.getInstance().getUrlPag(nomP);
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (URISyntaxException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        eliminarPag.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

    private void dialogEliminarCatDeLaPagina(){
        final String nomC = llista.getSelectionModel().getSelectedItem();
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la categoria " + nomC + " de la pàgina?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlCatPag.getInstance().esborrarArcPC(nomC, nomP);
                carregarCategoriesDeLaPagina();
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
        dialog.setTitle("Eliminar categoria de la pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void carregarCategoriesDeLaPagina(){
        llista.setItems(getCategoriesDeLaPagina());
        llista.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
    }

    private void dialogAfegirCat(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nom de la categoria a afegir a la pàgina");
        final ComboBox<String> nomCatAfegir = new ComboBox<>();
        nomCatAfegir.setPrefSize(300, 20);
        nomCatAfegir.setItems(getTotesCategories());
        new AutoCompleteComboBoxListener(nomCatAfegir);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNovaCat = nomCatAfegir.getValue();
                boolean existeix = CtrlCatPag.getInstance().existeixCategoria(nomNovaCat);
                if(existeix) {
                    CtrlCatPag.getInstance().RelPC(nomP, nomNovaCat);
                    carregarCategoriesDeLaPagina();
                    dialog.close();
                } else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No existeix la categoria o no has escrit cap nom");
                    alertDialog.show();
                }
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
        parent.getChildren().addAll(confirmation, nomCatAfegir, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Afegir categoria a la pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private ObservableList<String> getTotesCategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlWikipedia.getInstance().getNomsCategories());
        return data;
    }
    private ObservableList<String> getCategoriesDeLaPagina(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlCatPag.getInstance().getCategoriesPagina(nomP));
        return data;
    }

    private void dialogReanomenarPag(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nou nom de la pàgina");
        final TextField nomNouTextField = new TextField();
        nomNouTextField.setPrefSize(300, 20);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNou = nomNouTextField.getText();
                boolean existeix = CtrlCatPag.getInstance().existeixPagina(nomNou);
                if(!existeix && !nomNou.isEmpty()) {
                    CtrlCatPag.getInstance().ModificarNomPag(nomP, nomNou);
                    navegacioVista.carregarPagines();
                    nomP = nomNou;
                    titol.setText(nomP);
                    dialog.close();
                } else{
                    AlertDialog alertDialog = new AlertDialog("Error", "Ja existeix la pàgina o no has escrit cap nom");
                    alertDialog.show();
                }
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
        parent.getChildren().addAll(confirmation, nomNouTextField, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Reanomenar la pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
