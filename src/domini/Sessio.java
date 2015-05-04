package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import prop.classescompartides.graf.Graf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 22/3/15
 */

/**
 * Sessio de la Wikipedia
 */

public class Sessio {

    private static Sessio INSTANCE;

    private String dataCreacio;
    private GrafWikipedia grafWiki;
    private Graf<NodeCategoria> grafAlgoritme;

    private Sessio() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCreacio = new Date();
        this.dataCreacio = dateFormat.format(dataCreacio);
        grafWiki = new GrafWikipedia();
        grafAlgoritme = new Graf<NodeCategoria>();
    }

    /**
     * /**
     * Retorna una instancia de Sessio
     * @return una instancia de Sessio
     */
    public static Sessio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Sessio();
        }
        return INSTANCE;
    }

    /**
     * Retorna la data de creació de la sessió
     * @return la data de creació de la sessió
     */
    public String getDataCreacio() {
        return dataCreacio;
    }

    /**
     * Retorna el Graf de la Wikipedia
     * @return el graf de la Wikipedia
     */
    public GrafWikipedia getGrafWiki() {
        return grafWiki;
    }

    public void setGrafWiki(GrafWikipedia grafWiki) {
        this.grafWiki = grafWiki;
    }

    /**
     * Retorna el graf de categories pels algorismes
     * @return el graf de categories pels algorismes
     */
    public Graf<NodeCategoria> getGrafAlgoritme() {
        return grafAlgoritme;
    }

    public void setGrafAlgoritme(Graf<NodeCategoria> grafAlgoritme) {
        this.grafAlgoritme = grafAlgoritme;
    }
}
