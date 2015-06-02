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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentacio.autocompletat.AutoCompleteComboBoxListener;

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
    private Label titol;

    public NavegacioC(String nomCategoria,  NavegacioVista nav, Stage stage){
        nomC = nomCategoria;
        navegacioVista = nav;
        stagePropi = stage;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        parent.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parent, 1024, 768); // necessari perque es massa gran...

        titol = new Label(nomC);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);

        Label labelP = new Label("Pàgines que conté");
        llistaP = new ListView<>();
        llistaP.getItems().addAll(CtrlCatPag.getInstance().getPagines(nomC));
        Button accedirP = new Button("Accedir a la pàgina");
        accedirP.setMaxWidth(Double.MAX_VALUE);
        Button eliminarP = new Button("Eliminar pàgina de la categoria");
        eliminarP.setMaxWidth(Double.MAX_VALUE);
        Button afegirP = new Button("Afegir pàgina a la categoria");
        afegirP.setMaxWidth(Double.MAX_VALUE);
        VBox boxP = new VBox(SPACE);
        boxP.getChildren().addAll(labelP, llistaP, accedirP, eliminarP, afegirP);
        Label labelSuper = new Label("Supercategories");
        llistaSuper = new ListView<>();
        llistaSuper.getItems().addAll(CtrlCatPag.getInstance().getSuperCategories(nomC));
        Button accedirSuper = new Button("Accedir a la supercategoria");
        accedirSuper.setMaxWidth(Double.MAX_VALUE);
        Button eliminarSuper = new Button("Eliminar supercategoria de la categoria");
        eliminarSuper.setMaxWidth(Double.MAX_VALUE);
        Button afegirSuper = new Button("Afegir supercategoria a la categoria");
        afegirSuper.setMaxWidth(Double.MAX_VALUE);
        VBox boxSuper = new VBox(SPACE);
        boxSuper.getChildren().addAll(labelSuper, llistaSuper, accedirSuper, eliminarSuper, afegirSuper);
        Label labelSub = new Label("Subcategories");
        llistaSub = new ListView<>();
        llistaSub.getItems().addAll(CtrlCatPag.getInstance().getSubCategories(nomC));
        Button accedirSub = new Button("Accedir a la subcategoria");
        accedirSub.setMaxWidth(Double.MAX_VALUE);
        Button eliminarSub = new Button("Eliminar subcategoria de la categoria");
        eliminarSub.setMaxWidth(Double.MAX_VALUE);
        Button afegirSub = new Button("Afegir subcategoria a la categoria");
        afegirSub.setMaxWidth(Double.MAX_VALUE);
        VBox boxSub = new VBox(SPACE);
        boxSub.getChildren().addAll(labelSub, llistaSub, accedirSub, eliminarSub, afegirSub);

        HBox boxPagSuperSub = new HBox(SPACE);
        boxPagSuperSub.setAlignment(Pos.CENTER);
        boxPagSuperSub.getChildren().addAll(boxP, boxSuper, boxSub);

        Button modificarNom = new Button("Modificar el nom de la categoria");
        Button eliminar = new Button("Eliminar la categoria");
        modificarNom.setMaxWidth(200);
        eliminar.setMaxWidth(200);

        // llista amb cats del mateix tema
        Label labelTema = new Label("Categories del mateix tema");
        llistaTema = new ListView<>();
        llistaTema.getItems().addAll(CtrlCatPag.getInstance().getCategoriesTema(nomC));
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
        boxMock2.getChildren().addAll(mockList2, modificarNom, eliminar);

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
        eliminarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaP.getSelectionModel().isEmpty())
                    dialogEliminarPagDeLaCat();
                else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap pàgina seleccionada");
                    alertDialog.show();
                }
            }
        });
        afegirP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogAfegirPag();
            }
        });
        accedirSuper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaSuper);
            }
        });
        eliminarSuper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaSuper.getSelectionModel().isEmpty())
                    dialogEliminarSuperDeLaCat();
                else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap categoria seleccionada");
                    alertDialog.show();
                }
            }
        });
        afegirSuper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogAfegirSuperALaCat();
            }
        });
        accedirSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaSub);
            }
        });
        eliminarSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaSub.getSelectionModel().isEmpty())
                    dialogEliminarSubcategoriaDeLaCat();
                else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap categoria seleccionada");
                    alertDialog.show();
                }
            }
        });
        afegirSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogAfegirSubALaCat();
            }
        });
        accedirCatTema.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accedirCategoria(llistaTema);
            }
        });
        modificarNom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogReanomenarCat();
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
        } else {
            AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap categoria seleccionada");
            alertDialog.show();
        }
    }

    private void accedirPagina(ListView<String> llista){
        if (!llista.getSelectionModel().isEmpty()) {
            Stage stage = new Stage();
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
        else{
            AlertDialog alertDialog = new AlertDialog("Error", "No hi ha cap pàgina seleccionada");
            alertDialog.show();
        }
    }

    private void dialogReanomenarCat(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nou nom de la categoria");
        final TextField nomNouTextField = new TextField();
        nomNouTextField.setPrefSize(300, 20);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNou = nomNouTextField.getText();
                boolean existeix = CtrlCatPag.getInstance().existeixCategoria(nomNou);
                if(!existeix && !nomNou.isEmpty()) {
                    CtrlCatPag.getInstance().ModificarNomCat(nomC, nomNou);
                    navegacioVista.carregarCategories();
                    nomC = nomNou;
                    titol.setText(nomC);
                    dialog.close();
                } else{
                    AlertDialog alertDialog = new AlertDialog("Error", "Ja existeix la categoria o no has escrit cap nom");
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
        dialog.setTitle("Reanomenar la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private ObservableList<String> getPaginesDeLaCategoria(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlCatPag.getInstance().getPagines(nomC));
        return data;
    }
    private void carregarPaginesDeLaCategoria(){
        llistaP.setItems(getPaginesDeLaCategoria());
        llistaP.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
    }
    private ObservableList<String> getSupercategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlCatPag.getInstance().getSuperCategories(nomC));
        return data;
    }
    private void carregarSupercategories(){
        llistaSuper.setItems(getSupercategories());
        llistaSuper.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
    }
    private ObservableList<String> getSubcategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlCatPag.getInstance().getSubCategories(nomC));
        return data;
    }
    private void carregarSubcategories(){
        llistaSub.setItems(getSubcategories());
        llistaSub.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
    }

    private ObservableList<String> getTotesCategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlWikipedia.getInstance().getNomsCategories());
        return data;
    }
    private ObservableList<String> getTotesPagines(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlWikipedia.getInstance().getNomsPagines());
        return data;
    }


    private void dialogEliminarPagDeLaCat(){
        final String nomPagina = llistaP.getSelectionModel().getSelectedItem();
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la pàgina " + nomPagina + " de la categoria?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlCatPag.getInstance().esborrarArcPC(nomC, nomPagina);
                carregarPaginesDeLaCategoria();
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
        dialog.setTitle("Eliminar pàgina de la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogAfegirPag(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nom de la pàgina a afegir a la categoria");
        final ComboBox<String> nomPagAfegir = new ComboBox<>();
        nomPagAfegir.setPrefSize(300, 20);
        nomPagAfegir.setItems(getTotesPagines());
        new AutoCompleteComboBoxListener(nomPagAfegir);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNovaPag = nomPagAfegir.getValue();
                boolean existeix = CtrlCatPag.getInstance().existeixPagina(nomNovaPag);
                if(existeix) {
                    CtrlCatPag.getInstance().RelPC(nomNovaPag, nomC);
                    carregarPaginesDeLaCategoria();
                    dialog.close();
                } else{
                    AlertDialog alertDialog = new AlertDialog("Error", "No existeix la pàgina o no has escrit cap nom");
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
        parent.getChildren().addAll(confirmation, nomPagAfegir, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Afegir pàgina a la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogEliminarSuperDeLaCat(){
        final String nomSuper = llistaSuper.getSelectionModel().getSelectedItem();
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la supercategoria " + nomSuper + " de la categoria?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlCatPag.getInstance().esborrarArc(nomC, nomSuper);
                carregarSupercategories();
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
        dialog.setTitle("Eliminar supercategoria de la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogAfegirSuperALaCat(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nom de la supercategoria a afegir a la categoria");
        final ComboBox<String> nomSupercatAfegir = new ComboBox<>();
        nomSupercatAfegir.setPrefSize(300, 20);
        nomSupercatAfegir.setItems(getTotesCategories());
        new AutoCompleteComboBoxListener(nomSupercatAfegir);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNovaSuper = nomSupercatAfegir.getValue();
                boolean existeix = CtrlCatPag.getInstance().existeixCategoria(nomNovaSuper);
                if(existeix) {
                    if(!nomNovaSuper.equals(nomC)) {
                        CtrlCatPag.getInstance().RelCsupC(nomNovaSuper, nomC);
                        carregarSupercategories();
                        dialog.close();
                    } else{
                        AlertDialog alertDialog = new AlertDialog("Error","No es pot afegir: és la pròpia categoria");
                        alertDialog.show();
                    }
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
        parent.getChildren().addAll(confirmation, nomSupercatAfegir, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Afegir supercategoria a la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogEliminarSubcategoriaDeLaCat(){
        final String nomSub = llistaSub.getSelectionModel().getSelectedItem();
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Estàs segur de que vols eliminar la subcategoria " + nomSub + " de la categoria?");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CtrlCatPag.getInstance().esborrarArc(nomC, nomSub);
                carregarSubcategories();
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
        dialog.setTitle("Eliminar subcategoria de la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogAfegirSubALaCat(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Escriu el nom de la subcategoria a afegir a la categoria");
        final ComboBox<String> nomSubcatAfegir = new ComboBox<>();
        nomSubcatAfegir.setPrefSize(300, 20);
        nomSubcatAfegir.setItems(getTotesCategories());
        new AutoCompleteComboBoxListener(nomSubcatAfegir);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nomNovaSub = nomSubcatAfegir.getValue();
                boolean existeix = CtrlCatPag.getInstance().existeixCategoria(nomNovaSub);
                if(existeix) {
                    if(!nomNovaSub.equals(nomC)) {
                        CtrlCatPag.getInstance().RelCsubC(nomC, nomNovaSub);
                        carregarSubcategories();
                        dialog.close();
                    }else{
                        AlertDialog alertDialog = new AlertDialog("Error","No es pot afegir: és la pròpia categoria");
                        alertDialog.show();
                    }
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
        parent.getChildren().addAll(confirmation, nomSubcatAfegir, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Afegir subcategoria a la categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
