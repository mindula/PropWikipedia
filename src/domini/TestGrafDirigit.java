package domini;

import graf.GrafDirigit;
import org.grupwiki.graf.Arc;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/2/15
 */
public class TestGrafDirigit {
    public static void main(String args[]) {
        GrafDirigit<Integer> g = new GrafDirigit<Integer>();
        for (int i = 0; i < 3; ++i) {
            g.afegirNode(i);
        }
        g.afegirArc(new Arc<Integer>(2, 0, 1));
        g.afegirArc(new Arc<Integer>(2, 2, 1));
        g.afegirArc(new Arc<Integer>(2, 2, 0));

        System.out.println(g + " " + g.ordre() + " " + g.mida());
        System.out.println(g.getArcs());


        g.eliminarNode(0);

        System.out.println(g + " " + g.ordre() + " " + g.mida());

        System.out.println(g.getArcs());
        System.out.println(g.getNodes());
    }
}
