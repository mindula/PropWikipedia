package presentacio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 28/5/15
 */
public class FinestraPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Wikipedia");

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        /*
            Aqui afegim les TABs
         */
        tabPane.getTabs().addAll(
                new NavegacioVista(),
                new TemesVista(),
                new Importar());

        /*
            Aquin afegim els menus
         */
        Menu menu1 = new Menu("Arxiu");
        Menu menu2 = new Menu("Opcions");
        Menu menu3 = new Menu("Ajuda");
        menu1.getItems().add(new MenuItem("kek"));
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);


        //Abans era un StackPane
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, tabPane);
        Scene scene = new Scene(root,1024,720);
        //CSS per l'AutoFill
        scene.getStylesheets().add(getClass().getResource("control.css").toExternalForm());
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
