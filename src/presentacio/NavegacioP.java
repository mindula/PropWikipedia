package presentacio;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 29/05/15
 */

public class NavegacioP {

    private final double SPACE = 10;

    public Scene getScene(){
        VBox parent = new VBox(SPACE);
        parent.setPadding(new Insets(20));
        Scene scene = new Scene(parent);



        return scene;
    }
}
