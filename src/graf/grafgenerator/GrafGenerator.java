package graf.grafgenerator;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodeWiki;
import graf.grafgenerator.Criteris.Criteri;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Generador del graf pels algorismes a partir del graf de la Wikipedia
 */
public class GrafGenerator  {
    public Graf<NodeCategoria> generate(GrafWikipedia graf, ArrayList<Criteri> criteris) {
        Graf<NodeCategoria> newGraf = new Graf<NodeCategoria>();


        for (NodeWiki n1 : graf.getNodes()) {
            for (NodeWiki n2 : graf.getNodes()) { // si no fossin sets, podria millorar aixo.
                // actualment es mira cada parella de nodes a-b dos cops
                if (n1 != n2) {
                    if (n1.esCategoria() && n2.esCategoria()) {
                        NodeCategoria n1cat = (NodeCategoria) n1;
                        NodeCategoria n2cat = (NodeCategoria) n2;

                        double pes = 0;
                        for(Criteri c  :criteris)
                            pes += c.getPes(n1, n2, graf) * c.getPonderacio();

                        if (pes > 0) {
                            Arc<NodeCategoria> a = new Arc<NodeCategoria>(pes, n1cat, n2cat);
                            if (!newGraf.existeixNode(n1cat))
                                newGraf.afegirNode(n1cat);
                            if (!newGraf.existeixNode(n2cat))
                                newGraf.afegirNode(n2cat);
                            newGraf.afegirArc(a);
                        }
                    }
                }
            }
        }


        return newGraf;
    }
}
