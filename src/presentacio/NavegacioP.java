package presentacio;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 29/05/15
 */

public class NavegacioP {

    private final double SPACE = 10;
    private String nomP;

    public NavegacioP(String nomPagina){
        nomP = nomPagina;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Scene scene = new Scene(parent);

        Label titol = new Label(nomP);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);
        Label cats = new Label("Categories a les quals pertany");
        ListView<String> llista = new ListView<>();
        llista.getItems().addAll("QWERTTYUIOP", "asdf");
        Button accedir = new Button("Accedir a la categoria");
        Separator separator2 = new Separator(); separator2.setVisible(false);
        Button editar = new Button("Editar la pàgina");
        Button eliminar = new Button("Eliminar la pàgina");
        accedir.setMaxWidth(Double.MAX_VALUE);
        editar.setMaxWidth(Double.MAX_VALUE);
        eliminar.setMaxWidth(Double.MAX_VALUE);

        parent.getChildren().addAll(titol, separator1, cats, llista,
                accedir, separator2, editar, eliminar);

        return scene;
    }
}
