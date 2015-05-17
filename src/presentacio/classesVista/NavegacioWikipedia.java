package presentacio.classesVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/17/15
 */
public class NavegacioWikipedia implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void visualitzarPagina(ActionEvent event) {
        NavegadorVistes.loadVista(NomsVistes.VisualitzacioPagina);
    }
}
