package domini.modeldades;

import domini.modeldades.graf.NodeWiki;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

/**
 * Conte un NodeWiki i la data de realitzacio de la cerca. Veure classe Cerca per mes informacio
 */
public class InfoCerca {
    private ArrayList<? extends NodeWiki> a;
    private String data;

    private InfoCerca() {}

    /**
     * Constructor per defecte
     * @param a
     * @param data
     */
    public InfoCerca(ArrayList<? extends NodeWiki> a, String data) {
        this.a = a;
        this.data = data;
    }

    /**
     * Retorna el NodeWiki de la instancia
     * @return el NodeWiki de la instancia
     */
    public ArrayList<? extends NodeWiki> getResultats() {
        return a;
    }

    /**
     * Retorna el resultat de la cerca
     * @return el resultat de la cerca
     */
    public String getDataCerca() {
        return data;
    }

    @Override
    public String toString() {
        return "InfoCerca{" +
                "a=" + a +
                ", data='" + data + '\'' +
                '}';
    }
}
