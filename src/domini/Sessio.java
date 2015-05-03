package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import org.grupwiki.graf.Graf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 22/3/15
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

    public static Sessio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Sessio();
        }
        return INSTANCE;
    }

    public String getDataCreacio() {
        return dataCreacio;
    }

    public GrafWikipedia getGrafWiki() {
        return grafWiki;
    }

    public void setGrafWiki(GrafWikipedia grafWiki) {
        this.grafWiki = grafWiki;
    }

    public Graf<NodeCategoria> getGrafAlgoritme() {
        return grafAlgoritme;
    }

    public void setGrafAlgoritme(Graf<NodeCategoria> grafAlgoritme) {
        this.grafAlgoritme = grafAlgoritme;
    }
}
