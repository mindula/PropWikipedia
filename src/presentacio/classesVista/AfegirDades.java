package presentacio.classesVista;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/14/15
 */
public class AfegirDades implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void carregarDades(ActionEvent event) throws Exception {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(fileChooserStage);
    }
}
