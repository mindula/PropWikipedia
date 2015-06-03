package presentacio;

import domini.controladors.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistencia.CtrlPersistencia;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

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
    private FinestraPrincipal finestraPrincipal;

    private TabPane tabPane;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        finestraPrincipal = this;
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
        final EventHandler<ActionEvent> action = listenerMenuItems();
        Menu menu1 = new Menu("Arxiu");
        MenuItem nou = new MenuItem("Nou");
        nou.setOnAction(action);
        MenuItem guardar = new MenuItem("Guardar...");
        guardar.setOnAction(action);
        MenuItem carregar = new MenuItem("Carregar...");
        carregar.setOnAction(action);
        MenuItem importar = new MenuItem("Importar...");
        importar.setOnAction(action);
        final MenuItem sortir = new MenuItem("Sortir");
        sortir.setOnAction(action);
        menu1.getItems().addAll(nou, guardar, carregar, importar, sortir);

        Menu menu2 = new Menu("Visualitzar");
        MenuItem historialCerques = new MenuItem("Historial de cerques");
        historialCerques.setOnAction(action);
        MenuItem compararTemes = new MenuItem("Comparar dues execucions");
        compararTemes.setOnAction(action);
        MenuItem mostrarGrafWiki = new MenuItem("Graf de la Wikipedia");
        mostrarGrafWiki.setOnAction(action);
        MenuItem mostrarGrafTemes = new MenuItem("Graf amb els temes");
        mostrarGrafTemes.setOnAction(action);
        menu2.getItems().addAll(mostrarGrafWiki, mostrarGrafTemes);
        Menu menu3 = new Menu("Eines");
        menu3.getItems().addAll(historialCerques, compararTemes);
        Menu menu4 = new Menu("Ajuda");


        MenuItem ajudaOnline = new MenuItem("Ajuda Online");
        ajudaOnline.setOnAction(action);

        MenuItem about = new MenuItem("About");
        about.setOnAction(action);

        menu4.getItems().addAll(ajudaOnline, about);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

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

                if("Nou".equals(itemName)) {
                    CtrlWikipedia.getInstance().reset();
                    CtrlComunitat.getInstance().reset();
                    CtrlCatPag.getInstance().reset();
                    Historial.getInstance().reset();
                    navegacioVista.carregarCategories();
                    navegacioVista.carregarPagines();
                    temesVista.actualitzaTemes();
                    temesVista.netejarLlistaCats();

                }
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
                        } catch (Exception e) {
                            dialogAltertaImportar();
                        }
                        navegacioVista.carregarCategories();
                        navegacioVista.carregarPagines();
                        temesVista.actualitzaTemes();
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
                        } catch (Exception e) {
                            dialogAltertaImportar();
                        }
                        navegacioVista.carregarCategories();
                        navegacioVista.carregarPagines();
                    }
                }
                else if ("Sortir".equals(itemName)) Platform.exit();
                else if ("Historial de cerques".equals(itemName)) {
                    historialVista = new HistorialVista();
                    Stage stage = new Stage();
                    Scene scene = historialVista.getScene();
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.setTitle("Historial");
                    stage.show();
                }
                else if ("Comparar dues execucions".equals(itemName)) {
                    ComparacioExecucions comparacioExecucions = new ComparacioExecucions(stage, finestraPrincipal);
                    Scene scene = comparacioExecucions.getScene();
                    Stage stageComparacio = new Stage();
                    stageComparacio.setResizable(false);
                    stageComparacio.initModality(Modality.APPLICATION_MODAL);
                    stageComparacio.setScene(scene);
                    stageComparacio.setTitle("Comparar dues execucions");
                    stageComparacio.show();
                }
                else if ("Graf de la Wikipedia".equals(itemName)) {
                    CtrlDibuix ctrlDibuix = new CtrlDibuix();
                    ctrlDibuix.DibuixarGraf();
                }
                else if ("Graf amb els temes".equals(itemName)) {
                    CtrlDibuix ctrlDibuix = new CtrlDibuix();
                    ctrlDibuix.DibuixarGrafAmbComunitats();
                }

                else if(itemName.equals("Ajuda Online")){
                    String url = "https://drive.google.com/file/d/0B9yekx9QemWJd2c5Mmx4czhYM0E/view";
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (Exception e) {
                        AlertDialog alertDialog = new AlertDialog("Error", "Funcionalitat només disponible a Windows");
                        alertDialog.show();
                    }
                }
                else if (itemName.equals("About")) {
                    AlertDialog about = new AlertDialog("About", "Projecte de Programació" + '\n'
                                                                    + "Clúster 11" + '\n'
                                                                    + "Grup 3: Wikipedia" + '\n'
                                                                    + "Developers: " + '\n'
                                                                    + "Agustí Bau" + '\n'
                                                                    + "Eduard Casellas" + '\n'
                                                                    + "Ricard Gascons" + '\n'
                                                                    + "Aleix París" + '\n');
                }

            }
        };
    }

    private void dialogAltertaImportar() {
        final Stage dialog = new Stage();
        VBox parent = new VBox(10);
        parent.setPadding(new Insets(20));
        Label confirmation = new Label("El format especificat és incorrecte");
        Separator separator = new Separator(); separator.setVisible(false);
        HBox botons = new HBox(10);
        Button ok = new Button("D'acord");
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                dialog.close();
            }
        });
        botons.getChildren().addAll(ok);
        botons.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(confirmation, separator, botons);

        Scene dialogScene = new Scene(parent);
        dialog.setTitle("Format incorrecte!");
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void actualitzarTemes() {
        temesVista.actualitzaTemes();
    }

    public void bloquejarBotonsCatNavegacio() {
        navegacioVista.bloquejarBotonsCatNavegacio();
    }

    public void activarBotonsCatNavegacio() {
        navegacioVista.activarBotonsCatNavegacio();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
