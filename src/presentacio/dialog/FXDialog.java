package presentacio.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 29/5/15
 */
public abstract class FXDialog extends Stage {

    private Node parentNode;
    private String name;
    private Scene scene;
    private TextField textField;

    private FXDialog() {}

    public FXDialog(Node parentNode, String name) {
        this.parentNode = parentNode;
        this.name = name;
        setTitle(name);
        setWidth(500);
        setHeight(100);
        //no funciona en molts sistemes Linux
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        textField = new TextField();
        textField.setAlignment(Pos.BASELINE_CENTER);

        Button bOK = new Button("OK");
        bOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mostrarDialog();
            }
        });
        Button bCancel = new Button("Cancel");
        bCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });

        HBox buttonsBox = new HBox(50);
        buttonsBox.getChildren().addAll(bOK, bCancel);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox group = new VBox();
        group.getChildren().addAll(textField, buttonsBox);
        group.setAlignment(Pos.CENTER);
        scene = new Scene(group);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
                    close();
                }
            }
        });
        //setScene(scene);
    }

    protected String getName() {
        return name;
    }

    protected String getContent() {
        return textField.getText();
    }

    protected Scene getSceneDialog() {
        return scene;
    }

    protected TextField getTextField() {
        return textField;
    }

    public abstract String mostrarDialog();
}
