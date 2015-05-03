package domini;

import graf.NodeWiki;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

/**
 * Conté un NodeWiki i la data de realització de la cerca. Veure classe Cerca per mes informació
 */
public class InfoCerca {
    private NodeWiki a;
    private String data;

    private InfoCerca() {
        super();
    }

    /**
     * Constructor per defecte
     * @param a
     * @param data
     */
    public InfoCerca(NodeWiki a, String data) {
        this.a = a;
        this.data = data;
    }

    /**
     * Retorna el NodeWiki de la instancia
     * @return el NodeWiki de la instancia
     */
    public NodeWiki getResultat() {
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
