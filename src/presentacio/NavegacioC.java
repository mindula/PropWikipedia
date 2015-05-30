package presentacio;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 29/05/15
 */

public class NavegacioC {

    private final double SPACE = 10;
    private String nomC;
    private final NavegacioVista navegacioVista;

    public NavegacioC(String nomCategoria,  NavegacioVista nav){
        nomC = nomCategoria;
        navegacioVista = nav;
    }

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Scene scene = new Scene(parent);

        Label titol = new Label(nomC);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);

        Label labelP = new Label("Pàgines que conté");
        ListView<String> llistaP = new ListView<>(/****************/);
        llistaP.getItems().addAll("QWERTTYUIOP", "asdf");
        Button accedirP = new Button("Accedir a la pàgina");
        //accedirP.setMaxWidth(Double.MAX_VALUE);
        VBox boxP = new VBox(SPACE);
        boxP.getChildren().addAll(labelP, llistaP, accedirP);
        Label labelSuper = new Label("Supercategories");
        ListView<String> llistaSuper = new ListView<>(/****************/);
        llistaSuper.getItems().addAll("super1");
        Button accedirSuper= new Button("Accedir a la supercategoria");
        //setMaxWidth(Double.MAX_VALUE);
        VBox boxSuper = new VBox(SPACE);
        boxSuper.getChildren().addAll(labelSuper, llistaSuper, accedirSuper);
        Label labelSub = new Label("Subcategories");
        ListView<String> llistaSub = new ListView<>(/************/);
        llistaSub.getItems().addAll("sub1");
        Button accedirSub = new Button("Accedir a la subcategoria");
        //setMaxWidth(Double.MAX_VALUE);
        VBox boxSub = new VBox(SPACE);
        boxSub.getChildren().addAll(labelSub, llistaSub, accedirSub);

        HBox boxPagSuperSub = new HBox(SPACE);
        boxPagSuperSub.getChildren().addAll(boxP, boxSuper, boxSub);

        Button editar = new Button("Editar la categoria");
        Button eliminar = new Button("Eliminar la categoria");
        //editar.setMaxWidth(Double.MAX_VALUE);
        //eliminar.setMaxWidth(Double.MAX_VALUE);

        parent.getChildren().addAll(titol, separator1, boxPagSuperSub);

        // OnMouseClicked Listeners:


        return scene;
    }

}
