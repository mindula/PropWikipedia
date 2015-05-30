package presentacio.dialog;

import domini.controladors.CtrlComunitat;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 29/5/15
 */
public class TemesDialog extends FXDialog {

    private String content;

    public TemesDialog(Node parentNode, String name) {
        super(parentNode, name);
    }

    @Override
    public String mostrarDialog() {
        getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    content = getTextField().getText();
                }
            }
        });
        setScene(getSceneDialog());
        return content;
    }
}
