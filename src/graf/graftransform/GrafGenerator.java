package graf.graftransform;

import domini.NodeCategoria;
import domini.NodeWiki;
import graf.graftransform.Criteris.Criteri;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 30/04/15.
 */
public class GrafGenerator  {
    public Graf<NodeCategoria> generate(Graf<NodeWiki> graf, Criteri... criteris) {
        Graf<NodeCategoria> newGraf = new Graf<NodeCategoria>();


        for (NodeWiki n1 : graf.getNodes()) {
            for (NodeWiki n2 : graf.getNodes()) { // si no fossin sets, podria millorar aixo.
                // actualment es mira cada parella de nodes a-b dos cops
                if (n1 != n2) {
                    if (n1 instanceof NodeCategoria && n2 instanceof NodeCategoria) {
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
