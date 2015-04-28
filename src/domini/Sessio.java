package domini;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 3/22/15
 */

public class Sessio {

    private static Sessio INSTANCE;

    private String nom;
    private String dataCreacio;

    private Sessio() {}

    public static Sessio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Sessio();
        }
        return INSTANCE;
    }

    public Sessio (String nom) {
        this.nom = nom;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCreacio = new Date();
        this.dataCreacio = dateFormat.format(dataCreacio);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDataCreacio() {
        return dataCreacio;
    }
}
