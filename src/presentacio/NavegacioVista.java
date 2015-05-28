package presentacio;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;


/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 28/05/15
 */
public class NavegacioVista extends Tab {

    private final double SPACE = 10;

    public NavegacioVista(){
        setText("Navegació");

        TextField queryText = new TextField();
        ComboBox<String> pagCerca = new ComboBox<>();
        pagCerca.getItems().addAll("Pàgina", "Categoria");
        pagCerca.getSelectionModel().select(0);
        Button cercaButton = new Button("Cerca");

        HBox liniaCerca = new HBox(SPACE);
        liniaCerca.getChildren().addAll(queryText, pagCerca, cercaButton);
        liniaCerca.setAlignment(Pos.CENTER);

        Label pagLabel = new Label("Pàgines");
        Label catLabel = new Label("Categories");
        ListView<String> llistaP = new ListView<>();
        llistaP.getItems().addAll("hoho", "a");
        ListView llistaC = new ListView();
        llistaC.getItems().addAll("Cat1", "Cat2");
        Button accedirP = new Button("Accedir a pàgina");
        Button accedirC = new Button("Accedir a categoria");

        VBox boxP = new VBox(SPACE);
        VBox boxC = new VBox(SPACE);
        boxP.getChildren().addAll(pagLabel, llistaP, accedirP);
        boxC.getChildren().addAll(catLabel, llistaC, accedirC);
        boxP.setAlignment(Pos.CENTER);
        boxC.setAlignment(Pos.CENTER);

        HBox llistes = new HBox(SPACE);
        llistes.getChildren().addAll(boxP, boxC);
        llistes.setAlignment(Pos.CENTER);

        VBox parentBox = new VBox(SPACE);
        parentBox.getChildren().addAll(liniaCerca, llistes);
        parentBox.setPadding(new Insets(20));

        setContent(parentBox);
    }
}
/*
Label mylabel = new Label("mylabel");
Label label2 = new Label("label2");

HBox parentHBox = new HBox(10);
parentHBox.setPadding(new Insets(20));

parentHBox.getChildren().add( mylabel );
parentHBox.getChildren().add( label2 );
parentHBox.setAlignment(Pos.TOP_CENTER);  */