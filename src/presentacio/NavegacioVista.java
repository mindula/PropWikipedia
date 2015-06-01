package presentacio;

import domini.controladors.CtrlCatPag;
import domini.controladors.CtrlWikipedia;
import domini.controladors.Historial;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentacio.autocompletat.AutoCompleteComboBoxListener;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 28/05/15
 */

public class NavegacioVista extends Tab {

    private final double SPACE = 10;

    private final ComboBox<String> queryText;
    private final ComboBox<String> pagCatCerca;
    private final ListView<String> llistaP;
    private final ListView<String> llistaC;

    public NavegacioVista(){
        setText("Navegació i gestió de la Wikipedia");

        queryText = new ComboBox<>();
        queryText.setPrefSize(300, 20);
        queryText.getItems().add("No hi ha res a mostrar");
        new AutoCompleteComboBoxListener(queryText);

        pagCatCerca = new ComboBox<>();
        pagCatCerca.getItems().addAll("Pàgina", "Categoria");
        pagCatCerca.getSelectionModel().select(0);
        Button cercaButton = new Button("Seleccionar");
        HBox liniaCerca = new HBox(SPACE);
        liniaCerca.getChildren().addAll(queryText, pagCatCerca, cercaButton);
        liniaCerca.setAlignment(Pos.CENTER);

        Label pagLabel = new Label("Pàgines");
        Label catLabel = new Label("Categories");
        llistaP = new ListView<>();
        llistaP.getItems().addAll( getPagines() );
        llistaC = new ListView<>();
        llistaC.getItems().addAll( getCategories() );
        final Button accedirP = new Button("Accedir a pàgina");
        accedirP.setMaxWidth(Double.MAX_VALUE);
        Button accedirC = new Button("Accedir a categoria");
        accedirC.setMaxWidth(Double.MAX_VALUE);
        Button novaP = new Button("Nova pàgina");
        novaP.setMaxWidth(Double.MAX_VALUE);
        Button novaC = new Button("Nova categoria");
        novaC.setMaxWidth(Double.MAX_VALUE);
        Button eliminarP = new Button("Eliminar pàgina");
        eliminarP.setMaxWidth(Double.MAX_VALUE);
        Button eliminarC = new Button("Eliminar categoria");
        eliminarC.setMaxWidth(Double.MAX_VALUE);

        VBox boxP = new VBox(SPACE);
        VBox boxC = new VBox(SPACE);
        Separator separatorP = new Separator(); separatorP.setVisible(false);
        Separator separatorC = new Separator(); separatorC.setVisible(false);
        boxP.getChildren().addAll(pagLabel, llistaP,
                accedirP, separatorP, eliminarP, novaP);
        boxC.getChildren().addAll(catLabel, llistaC,
                accedirC, separatorC, eliminarC, novaC);
        boxP.setAlignment(Pos.CENTER);
        boxC.setAlignment(Pos.CENTER);

        HBox llistes = new HBox(SPACE);
        llistes.getChildren().addAll(boxP, boxC);
        llistes.setAlignment(Pos.CENTER);

        final VBox parentBox = new VBox(SPACE);
        parentBox.getChildren().addAll(liniaCerca, llistes);
        parentBox.setPadding(new Insets(20));

        // OnMouseClicked Listeners:

        cercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String cercat = queryText.getValue();
                if (!pagCatCerca.getSelectionModel().getSelectedItem().equals("Categoria")
                        && CtrlCatPag.getInstance().existeixPagina(cercat)){ // pàgina
                    llistaP.getSelectionModel().select(cercat);
                    int index = llistaP.getSelectionModel().getSelectedIndex();
                    llistaP.scrollTo(index);
                }
                else if(pagCatCerca.getSelectionModel().getSelectedItem().equals("Categoria")
                        && CtrlCatPag.getInstance().existeixCategoria(cercat)){ // categoria
                    llistaC.getSelectionModel().select(cercat);
                    int index = llistaC.getSelectionModel().getSelectedIndex();
                    llistaC.scrollTo(index);
                }
                else System.out.println("No existeix");
                //Historial.getInstance().afegirCerca(cercat); // TODO: solucionar bug
            }
        });
        accedirP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!llistaP.getSelectionModel().isEmpty()) {
                    Stage stage = new Stage();
                    NavegacioP navegacioP = new NavegacioP(llistaP.getSelectionModel().getSelectedItem(),
                            NavegacioVista.this, stage);
                    Scene scene = navegacioP.getScene();
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.setTitle("Pàgina");
                    stage.show();
                    llistaP.getSelectionModel().clearSelection(); // si no, hi ha bugs de seleccio
                    llistaC.getSelectionModel().clearSelection();
                }
                else System.out.println("No hi ha pag seleccionada");
            }
        });
        accedirC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!llistaC.getSelectionModel().isEmpty()) {
                    Stage stage = new Stage();
                    NavegacioC navegacioC = new NavegacioC(llistaC.getSelectionModel().getSelectedItem(),
                            NavegacioVista.this, stage);
                    Scene scene = navegacioC.getScene();
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.setTitle("Categoria");
                    stage.show();
                    llistaP.getSelectionModel().clearSelection(); // si no, hi ha bugs de seleccio
                    llistaC.getSelectionModel().clearSelection();
                }
                else System.out.println("No hi ha cat seleccionada");
            }
        });
        novaP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogNovaPagina();
            }
        });
        novaC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialogNovaCategoria();
            }
        });
        eliminarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaP.getSelectionModel().isEmpty())
                    dialogEliminarPagina();
                else System.out.println("No hi ha pag seleccionada");
            }
        });
        eliminarC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!llistaC.getSelectionModel().isEmpty())
                    dialogEliminarCategoria();
                else System.out.println("No hi ha cat seleccionada");
            }
        });
        pagCatCerca.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.equals("Categoria")) carregarCategories();
                else carregarPagines();
            }
        });

        // finalment
        setContent(parentBox);
    }

    private ObservableList<String> getCategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlWikipedia.getInstance().getNomsCategories());
        return data;
    }
    private ObservableList<String> getPagines(){
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(CtrlWikipedia.getInstance().getNomsPagines());
        return data;
    }

    public void carregarPagines(){
        ObservableList<String> data = getPagines();
        llistaP.setItems(data);
        llistaP.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
        if (pagCatCerca.getValue().equals(pagCatCerca.getItems().get(0))) // pagina
            queryText.setItems(data);
    }

    public void carregarCategories(){
        ObservableList<String> data = getCategories();
        llistaC.setItems(data);
        llistaC.getSelectionModel().clearSelection(); // per evitar problemes de quin esta seleccionat si borrem dades
        if (pagCatCerca.getValue().equals(pagCatCerca.getItems().get(1))) // categoria
            queryText.setItems(data);
    }

    private void dialogEliminarPagina(){
        final String nomP = llistaP.getSelectionModel().getSelectedItem();
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
                carregarPagines();
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
        dialog.setTitle("Eliminar pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogEliminarCategoria(){
        final String nomC = llistaC.getSelectionModel().getSelectedItem();
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
                carregarCategories();
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
        dialog.setTitle("Eliminar categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogNovaPagina(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Introdueix el nom de la nova pàgina");
        final TextField textField = new TextField();
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!textField.getText().isEmpty() && !CtrlCatPag.getInstance().existeixPagina(textField.getText())) {
                    CtrlWikipedia.getInstance().afegirPag(textField.getText());
                    carregarPagines();
                    dialog.close();
                } else System.out.println("Ja existeix pag");
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
        parent.getChildren().addAll(confirmation, textField, separator, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Nova pàgina");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void dialogNovaCategoria(){
        final Stage dialog = new Stage();
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("Introdueix el nom de la nova categoria");
        final TextField textField = new TextField();
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(SPACE);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!textField.getText().isEmpty() && !CtrlCatPag.getInstance().existeixCategoria(textField.getText())) {
                    CtrlWikipedia.getInstance().afegirCat(textField.getText());
                    carregarCategories();
                    dialog.close();
                } else System.out.println("Ja existeix cat");
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
        parent.getChildren().addAll(confirmation, textField, separator, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Nova categoria");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}

/*
HBox parentHBox = new HBox(10);
parentHBox.setPadding(new Insets(20));
parentHBox.getChildren().addAll( ... );
parentHBox.setAlignment(Pos.TOP_CENTER);  */