package presentacio;

import domini.controladors.CtrlAlgorisme;
import domini.controladors.CtrlWikipedia;
import domini.controladors.graf.grafgenerator.Criteris.*;
import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.TipusAlgorisme;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.PrintStream;
import java.util.ArrayList;


/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 30/5/15
 */
public class GenerarTemes extends Tab {

    private FinestraPrincipal finestraPrincipal;

    private RadioButton louvainRadioB;
    private RadioButton cliqueRadioB;
    private RadioButton girvanRadioB;
    private Slider nomSlider;
    private Slider subCatSlider;
    private Slider superCatSlider;
    private Slider pagSlider;
    private Slider exhaustivitatSlider;
    private Button generarTemes;
    private ProgressBar progresAlgoritme;
    private TextArea logAlgorisme;
    private Consola consola;
    private PrintStream ps;

    public GenerarTemes(FinestraPrincipal finestraPrincipal) {
        this.finestraPrincipal = finestraPrincipal;
        setText("Generar temes");

        Label algoritmeLabel = new Label("Algoritme");
        Label exhaustivitatLabel = new Label("Exhaustivitat");
        Label criterisLabel = new Label("Criteris");

        ToggleGroup group = new ToggleGroup();
        louvainRadioB = new RadioButton("Louvain");
        louvainRadioB.setToggleGroup(group);
        cliqueRadioB = new RadioButton("Clique");
        cliqueRadioB.setToggleGroup(group);
        girvanRadioB = new RadioButton("Girvan Newman");
        girvanRadioB.setToggleGroup(group);

        exhaustivitatSlider = new Slider();
        exhaustivitatSlider.setMin(0);
        exhaustivitatSlider.setMax(100);
        exhaustivitatSlider.setValue(50);
        exhaustivitatSlider.setShowTickLabels(true);
        exhaustivitatSlider.setShowTickMarks(false);
        exhaustivitatSlider.setMajorTickUnit(50);
        exhaustivitatSlider.setMinorTickCount(5);
        exhaustivitatSlider.setBlockIncrement(1);

        generarTemes = new Button("Generar temes");
        generarTemes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generarTemes();
            }
        });

        EventHandler<ActionEvent> action = listenerCheckBox();
        CheckBox nomCheck = new CheckBox("Nom");
        nomCheck.setOnAction(action);
        CheckBox subCatComCheck = new CheckBox("Subcategories comunes");
        subCatComCheck.setOnAction(action);
        CheckBox superCatComCheck = new CheckBox("Supercategories comunes");
        superCatComCheck.setOnAction(action);
        CheckBox pagComunes = new CheckBox("Pàgines comunes");
        pagComunes.setOnAction(action);


        nomSlider = new Slider();
        nomSlider.setMin(0);
        nomSlider.setMax(100);
        nomSlider.setValue(0);
        nomSlider.setBlockIncrement(1);
        nomSlider.setDisable(true);
        subCatSlider = new Slider();
        subCatSlider.setMin(0);
        subCatSlider.setMax(100);
        subCatSlider.setValue(0);
        subCatSlider.setBlockIncrement(1);
        subCatSlider.setDisable(true);
        superCatSlider = new Slider();
        superCatSlider.setMin(0);
        superCatSlider.setMax(100);
        superCatSlider.setValue(0);
        superCatSlider.setBlockIncrement(1);
        superCatSlider.setDisable(true);
        pagSlider = new Slider();
        pagSlider.setMin(0);
        pagSlider.setMax(100);
        pagSlider.setValue(0);
        pagSlider.setBlockIncrement(1);
        pagSlider.setDisable(true);

        progresAlgoritme = new ProgressBar(0);
        progresAlgoritme.setMaxHeight(100);
        progresAlgoritme.setMaxWidth(Double.MAX_VALUE);

        logAlgorisme = new TextArea();
        logAlgorisme.setEditable(false);

        /*
            Preparaments per redirigir System.out a la TextArea logAlgorisme
         */
        consola = new Consola(logAlgorisme);
        ps = new PrintStream(consola, true);

        VBox algRadioBox = new VBox(5);
        algRadioBox.getChildren().addAll(algoritmeLabel, louvainRadioB, cliqueRadioB, girvanRadioB);
        VBox generarBox = new VBox(5);
        generarBox.getChildren().addAll(exhaustivitatLabel, exhaustivitatSlider, generarTemes);
        generarBox.setAlignment(Pos.CENTER);

        HBox part1 = new HBox(315);
        part1.getChildren().addAll(algRadioBox, generarBox);
        part1.setMaxWidth(Double.MAX_VALUE);
        part1.setMaxHeight(Double.MAX_VALUE);
        part1.setAlignment(Pos.TOP_CENTER);

        VBox checkBox = new VBox(5);
        checkBox.getChildren().addAll(nomCheck, subCatComCheck, superCatComCheck, pagComunes);
        VBox sliderBox = new VBox(5);
        sliderBox.getChildren().addAll(nomSlider, subCatSlider, superCatSlider, pagSlider);

        HBox innerPart2 = new HBox(250);
        innerPart2.getChildren().addAll(checkBox, sliderBox);
        innerPart2.setAlignment(Pos.CENTER);
        VBox part2 = new VBox(5);
        part2.getChildren().addAll(criterisLabel, innerPart2);
        part2.setMaxWidth(Double.MAX_VALUE);
        part2.setMaxHeight(Double.MAX_VALUE);
        part2.setAlignment(Pos.CENTER);

        VBox part3 = new VBox(10);
        part3.getChildren().addAll(progresAlgoritme, logAlgorisme);
        part3.setAlignment(Pos.BOTTOM_CENTER);

        VBox parentBox = new VBox(50);
        parentBox.setPadding(new Insets(20));
        parentBox.getChildren().addAll(part1, part2, part3);
        parentBox.setAlignment(Pos.CENTER);
        parentBox.setMaxWidth(Double.MAX_VALUE);
        parentBox.setMaxHeight(Double.MAX_VALUE);

        setContent(parentBox);

    }

    private void generarTemes() {
        //System.setErr(ps);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progresAlgoritme.setProgress(-1.0D);
                    }
                });
                double parametreAlgorisme = exhaustivitatSlider.getValue()/100;
                TipusAlgorisme algorisme = null;
                if (louvainRadioB.isSelected()) algorisme = TipusAlgorisme.LOUVAIN;
                else if (cliqueRadioB.isSelected()) algorisme = TipusAlgorisme.CLIQUE;
                else if (girvanRadioB.isSelected()) algorisme = TipusAlgorisme.GIRVAN;
                if (algorisme != null) {
                    double ponderacioNom;
                    double ponderacioSubC;
                    double ponderacioSuperC;
                    double ponderacioPag;
                    ArrayList<Criteri> criteris = new ArrayList<>();
                    if (!nomSlider.isDisable()) {
                        ponderacioNom = nomSlider.getValue();
                        Criteri c = new CriteriNom(ponderacioNom);
                        criteris.add(c);
                    }
                    if (!subCatSlider.isDisable()) {
                        ponderacioSubC = subCatSlider.getValue();
                        Criteri c = new CriteriSubCategoriesComuns(ponderacioSubC);
                        criteris.add(c);
                    }
                    if (!superCatSlider.isDisable()) {
                        ponderacioSuperC = superCatSlider.getValue();
                        Criteri c = new CriteriSuperCategoriesComuns(ponderacioSuperC);
                        criteris.add(c);
                    }
                    if (!pagSlider.isDisable()) {
                        ponderacioPag = pagSlider.getValue();
                        Criteri c = new CriteriPaginesComuns(ponderacioPag);
                        criteris.add(c);
                    }
                    CtrlAlgorisme c = new CtrlAlgorisme(
                            CtrlWikipedia.getInstance().getGrafWiki(),
                            algorisme,
                            parametreAlgorisme,
                            criteris);
                    ConjuntComunitatWiki comunitats = c.cercarComunitats();
                    System.out.println(comunitats.getInformacio().toString());

                    CtrlWikipedia.getInstance().setConjuntsGenerats(comunitats.getCjtComunitats());
                    System.out.println("Comunitats trobades:");
                    System.out.println(comunitats.getCjtComunitats());
                    //TODO: ConjuntComunitatsWiki no s'actualitza al acabar l'algorisme
                    finestraPrincipal.actualitzarTemes();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progresAlgoritme.setProgress(0);
                    }
                });
            }
        });
        t1.start();
    }

    private EventHandler<ActionEvent> listenerCheckBox() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CheckBox checkBox = (CheckBox) event.getSource();
                String titol = checkBox.getText();
                if ("Nom".equals(titol)) {
                    nomSlider.setDisable(!nomSlider.isDisable());
                }
                else if ("Subcategories comunes".equals(titol)) {
                    subCatSlider.setDisable(!subCatSlider.isDisable());
                }
                else if ("Supercategories comunes".equals(titol)) {
                    superCatSlider.setDisable(!superCatSlider.isDisable());
                }
                else if ("Pàgines comunes".equals(titol)) {
                    pagSlider.setDisable(!pagSlider.isDisable());
                }
            }
        };
    }
}
