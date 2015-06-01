package domini.modeldades;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

import java.io.Serializable;

/**
 * Conte un NodeWiki i la data de realitzacio de la cerca. Veure classe Cerca per mes informacio
 */
public class InfoCerca implements Serializable{
    private String a;
    private String data;

    private InfoCerca() {}

    /**
     * Constructor per defecte
     * @param a
     * @param data
     */
    public InfoCerca(String a, String data) {
        this.a = a;
        this.data = data;
    }

    /**
     * Retorna el NodeWiki de la instancia
     * @return el NodeWiki de la instancia
     */
    public String getResultat() {
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
