package presentacio;

import domini.controladors.CtrlWikipedia;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentacio.swingold.Temes;

import java.io.File;
import java.io.IOException;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 28/5/15
 */
public class FinestraPrincipal extends Application {

    private NavegacioVista navegacioVista;
    private TemesVista temesVista;
    private TabPane tabPane;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Wikipedia");
        stage.setMinHeight(384);
        stage.setMinWidth(512);

        navegacioVista = new NavegacioVista();
        temesVista = new TemesVista();

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        /*
            Aqui afegim les TABs
         */
        tabPane.getTabs().addAll(
                navegacioVista,
                temesVista);

        /*
            Aquin afegim els menus
         */
        EventHandler<ActionEvent> action = listenerMenuItems();
        Menu menu1 = new Menu("Arxiu");
        MenuItem nou = new MenuItem("Nou");
        nou.setOnAction(action);
        MenuItem guardar = new MenuItem("Guardar...");
        guardar.setOnAction(action);
        MenuItem carregar = new MenuItem("Carregar...");
        carregar.setOnAction(action);
        MenuItem importar = new MenuItem("Importar...");
        importar.setOnAction(action);
        MenuItem sortir = new MenuItem("Sortir");
        sortir.setOnAction(action);
        menu1.getItems().addAll(nou, guardar, carregar, importar, sortir);

        Menu menu2 = new Menu("Opcions");
        Menu menu3 = new Menu("Ajuda");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);


        //Abans era un StackPane
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, tabPane);
        scene = new Scene(root,1024,768);

        //CSS per l'AutoFill
        scene.getStylesheets().add(getClass().getResource("control.css").toExternalForm());

        stage.setScene(scene);

        stage.show();
    }

    private EventHandler<ActionEvent> listenerMenuItems() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem mItem = (MenuItem) event.getSource();
                String itemName = mItem.getText();

                if ("Nou...".equals(itemName)) System.out.println("no implementat");
                else if ("Guardar...".equals(itemName)) System.out.println("no implementat");
                else if ("Carregar...".equals(itemName)) System.out.println("no implementat");
                else if ("Importar...".equals(itemName)) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open Resource File");
                    File file = fileChooser.showOpenDialog(new Stage());
                    if (file != null) {
                        System.out.println(file);
                        try {
                            CtrlWikipedia.getInstance().getGrafWikiFromFile(file.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        navegacioVista.carregarCategories();
                        navegacioVista.carregarPagines();

                    }
                }
                else if ("Sortir".equals(itemName)) Platform.exit();
            }
        };
    }

    public void reloadVista() {
        com.sun.javafx.css.StyleManager.getInstance().reloadStylesheets(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
