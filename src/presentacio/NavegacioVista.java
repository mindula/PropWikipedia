package presentacio;

import domini.controladors.CtrlWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;
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

import java.util.ArrayList;


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

    private Stage stage;


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
                if (pagCatCerca.getValue().equals(pagCatCerca.getItems().get(0))){ // pàgina
                    llistaP.getSelectionModel().select(cercat);

                }
                else{
                    llistaC.getSelectionModel().select(cercat);
                }
            }
        });
        accedirP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NavegacioP navegacioP = new NavegacioP("Nom de la pàgina", NavegacioVista.this);
                Scene scene = navegacioP.getScene();
                stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Pàgina");
                stage.show();
            }
        });
        accedirC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NavegacioC navegacioC = new NavegacioC("Nom de la categoria", NavegacioVista.this);
                Scene scene = navegacioC.getScene();
                stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Pàgina");
                stage.show();
            }
        });
        novaP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //
            }
        });
        novaC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //
            }
        });
        eliminarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //
            }
        });
        eliminarC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //
            }
        });

        // finalment
        setContent(parentBox);
    }

    private ObservableList<String> getCategories(){
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<NodeCategoria> array = CtrlWikipedia.getInstance().getGrafWiki().getCategories();
        for (NodeCategoria anArray : array) {
            data.add(anArray.getNom());
        }
        return data;
    }
    private ObservableList<String> getPagines(){
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<NodePagina> array = CtrlWikipedia.getInstance().getGrafWiki().getPagines();
        for (NodePagina anArray : array) {
            data.add(anArray.getNom());
        }
        return data;
    }

    public void carregarPagines(){
        ObservableList<String> data = getPagines();
        llistaP.setItems(data);
        if (pagCatCerca.getValue().equals(pagCatCerca.getItems().get(0))) // pagina
            queryText.setItems(data);
    }

    public void carregarCategories(){
        ObservableList<String> data = getCategories();
        llistaC.setItems(data);
        if (pagCatCerca.getValue().equals(pagCatCerca.getItems().get(1))) // categoria
            queryText.setItems(data);
    }

    public void tancaFill(){
        stage.close();
    }


}


/*
HBox parentHBox = new HBox(10);
parentHBox.setPadding(new Insets(20));
parentHBox.getChildren().addAll( ... );
parentHBox.setAlignment(Pos.TOP_CENTER);  */