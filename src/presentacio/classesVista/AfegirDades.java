package presentacio.classesVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/14/15
 */
public class AfegirDades implements Initializable {
    @FXML
    TextField pathFitxer;
    @FXML
    Button menuPrincipal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void carregarDades(ActionEvent event) throws Exception {
        final Stage fileChooserStage = new Stage();
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(fileChooserStage);
        if (file != null) {
            pathFitxer.setText(file.toString());
        }
    }

    @FXML
    public void anarMenuPrincipal(ActionEvent event) throws Exception {
        NavegadorVistes.loadVista(NomsVistes.MenuPrincipal);
    }
}
