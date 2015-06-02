package domini.controladors;

import domini.modeldades.InfoCerca;
import prop.classescompartides.utils.Pair;

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

    public void reset() {
        llistatCerques = CtrlWikipedia.getInstance().getLlistatCerques();
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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        InfoCerca novaCerca = new InfoCerca(cerca, dateFormat.format(date));
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

    public ArrayList<Pair<String, String>> getCerquesStrings() {
        ArrayList<Pair<String, String>> res = new ArrayList<>();
        for (InfoCerca i : llistatCerques) res.add(new Pair<>(i.getResultat(),
                i.getDataCerca()));
        return res;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "llistatCerques=" + llistatCerques +
                '}';
    }
}
