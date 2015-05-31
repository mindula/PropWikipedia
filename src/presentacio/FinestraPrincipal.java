package presentacio;

import domini.controladors.CtrlWikipedia;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import persistencia.CtrlPersistencia;

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
    private GenerarTemes generarTemes;
    private HistorialVista historialVista;

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
        generarTemes = new GenerarTemes(this);

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        /*
            Aqui afegim les TABs
         */
        tabPane.getTabs().addAll(
                navegacioVista,
                temesVista,
                generarTemes);

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

        Menu menu2 = new Menu("Visualitzar");
        MenuItem historialCerques = new MenuItem("Historial de cerques");
        historialCerques.setOnAction(action);
        MenuItem mostrarGrafWiki = new MenuItem("Graf de la Wikipedia");
        mostrarGrafWiki.setOnAction(action);
        menu2.getItems().addAll(historialCerques, mostrarGrafWiki);
        Menu menu3 = new Menu("Ajuda");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);


        //Abans era un StackPane
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, tabPane);
        scene = new Scene(root,1024,768);

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
                else if ("Guardar...".equals(itemName)) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Guardar sessió...");
                    File file = fileChooser.showSaveDialog(new Stage());
                    if (file != null) {
                        System.out.println(file);
                        try {
                            CtrlPersistencia.guardarSessio(file.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if ("Carregar...".equals(itemName)) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Carregar sessió...");
                    File file = fileChooser.showOpenDialog(new Stage());
                    if (file != null) {
                        System.out.println(file);
                        try {
                            CtrlPersistencia.carregarSessio(file.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        navegacioVista.carregarCategories();
                        navegacioVista.carregarPagines();

                    }
                }
                else if ("Importar...".equals(itemName)) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Importar fitxer...");
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
                else if ("Historial de cerques".equals(itemName)) historialVista = new HistorialVista();
                else if ("Graf de la Wikipedia".equals(itemName)) System.out.println("no implementat");
            }
        };
    }

    public void actualitzarTemes() {
        temesVista.actualitzaTemes();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
