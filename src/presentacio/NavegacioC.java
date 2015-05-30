package presentacio;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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

    public NavegacioC(String nomCategoria){
        nomC = nomCategoria;
    }
    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Scene scene = new Scene(parent);

        Label titol = new Label(nomC);
        titol.setFont(new Font(30));
        Separator separator1 = new Separator(); separator1.setVisible(false);


        parent.getChildren().addAll(titol, separator1);

        // OnMouseClicked Listeners:


        return scene;
    }

}
