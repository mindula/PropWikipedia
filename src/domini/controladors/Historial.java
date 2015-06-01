package domini.controladors;

import domini.modeldades.InfoCerca;
import domini.modeldades.graf.NodeCategoria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

/**
 * Historial de cerques de la wikipedia
 * Singleton
 */

public class Historial {
    private static Historial INSTANCE;
    private ArrayList<InfoCerca> llistatCerques;

    private Historial() {
        llistatCerques = CtrlWikipedia.getInstance().getLlistatCerques();
    }

    /**
     * Retorna una instancia de Historial
     * @return una instancia de Historial
     */
    public static Historial getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Historial();
        }
        return INSTANCE;
    }

    /**
     * Afegeix una cerca realitzada a la Wikipedia
     * @param cerca
     */
    public void afegirCerca(InfoCerca cerca) {
        llistatCerques.add(cerca);
    }

    /**
     * Elimina una cerca realitzada a la Wikipedia
     * @param cerca
     */
    public void eliminarCerca(InfoCerca cerca) {
        llistatCerques.remove(cerca);
    }

    /**
     * Retorna el nombre de cerques que s'han realitzat a la sessio
     * @return el nombre de cerques que s'han realitzat a la sessio
     */
    public int quantesCerques() {
        return llistatCerques.size();
    }

    public void afegirCerca(String cerca) {
        NodeCategoria nodeC = CtrlWikipedia.getInstance().getGrafWiki().getNodeCat(cerca);
        ArrayList<NodeCategoria> resultat = new ArrayList<>(1);
        resultat.add(nodeC);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        InfoCerca novaCerca = new InfoCerca(resultat, dateFormat.format(date));
        llistatCerques.add(novaCerca);
    }

    public void netejarHistorial() {
        llistatCerques.clear();
    }

    /**
     * Retorna un llistat de totes les cerques que s'han realitzat a la sessio
     * @return un llistat de totes les cerques que s'han realitzat a la sessio
     */
    public List<InfoCerca> getCerques() {
        return Collections.unmodifiableList(llistatCerques);
    }

    @Override
    public String toString() {
        return "Historial{" +
                "llistatCerques=" + llistatCerques +
                '}';
    }
}
