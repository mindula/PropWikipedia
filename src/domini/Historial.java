package domini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

public class Historial {
    private static Historial INSTANCE;
    private ArrayList<InfoCerca> llistatCerques;

    private Historial() {
        llistatCerques = new ArrayList<InfoCerca>();
    }

    public static Historial getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Historial();
        }
        return INSTANCE;
    }

    public void afegirCerca(InfoCerca cerca) {
        llistatCerques.add(cerca);
    }

    public void eliminarCerca(InfoCerca cerca) {
        llistatCerques.remove(cerca);
    }

    public int quantesCerques() {
        return llistatCerques.size();
    }

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
