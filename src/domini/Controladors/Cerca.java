package domini.controladors;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

/**
 * Cerca de nodes dins la Wikipedia.
 */

import domini.JaroWinklerDistance;
import domini.modeldades.InfoCerca;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Cerca {

    private Cerca() {}

    /**
     * Cerca una Pagina dins la Wikipedia
     * @param g
     * @param query
     * @return un objecte tipus InfoCerca, compost per una data de cerca i el resultat
     * @throws Exception si no sha trobat el resultat desitjat
     */
    public static InfoCerca cercarWikipediaP (GrafWikipedia g, String query) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return new InfoCerca(cercarPagina(g, query), dateFormat.format(date));
    }

    /**
     * Cerca una Categoria dins la Wikipedia
     * @param g
     * @param query
     * @return un objecte tipus InfoCerca, compost per una data de cerca i el resultat
     * @throws Exception si no sha trobat el resultat desitjat
     */
    public static InfoCerca cercarWikipediaC (GrafWikipedia g, String query) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return new InfoCerca(cercaCategoria(g, query), dateFormat.format(date));
    }

    private static ArrayList<NodeCategoria> cercaCategoria (GrafWikipedia g, final String query) throws Exception {
        ArrayList<NodeCategoria> llistaResultats = new ArrayList<>();
        int size = query.length();
        for (NodeCategoria n : g.getCategories()) {
             if (JaroWinklerDistance.calculate(query, n.getNom()) >= 0.7) {
                 llistaResultats.add(n);
                 //if (llistaResultats.size() > 20) break;
             }
        }
        if (llistaResultats.size() == 0) throw new Exception("No s'ha trobat cap resultat");
        Collections.sort(llistaResultats, new Comparator<NodeCategoria>() {
            @Override
            public int compare(NodeCategoria o1, NodeCategoria o2) {
                double d1 = JaroWinklerDistance.calculate(query, o1.getNom());
                double d2 = JaroWinklerDistance.calculate(query, o2.getNom());
                if (d1 > d2) return -1;
                else if (d1 == d2) return 0;
                return 1;
            }
        });
        return llistaResultats;
    }

    private static ArrayList<NodePagina> cercarPagina (GrafWikipedia g, final String query) throws Exception {
        ArrayList<NodePagina> llistaResultats = new ArrayList<>();
        int size = query.length();
        for (NodePagina n : g.getPagines()) {
            if (JaroWinklerDistance.calculate(query, n.getNom()) >= 0.7) {
                llistaResultats.add(n);
                //if (llistaResultats.size() > 20) break;
            }
        }
        if (llistaResultats.size() == 0) throw new Exception("No s'ha trobat cap resultat");
        Collections.sort(llistaResultats, new Comparator<NodePagina>() {
            @Override
            public int compare(NodePagina o1, NodePagina o2) {
                double d1 = JaroWinklerDistance.calculate(query, o1.getNom());
                double d2 = JaroWinklerDistance.calculate(query, o2.getNom());
                if (d1 > d2) return -1;
                else if (d1 == d2) return 0;
                return 1;
            }
        });
        return llistaResultats;
    }
}