package graf.graftransform.Criteris;

import graf.NodeWiki;
import org.grupwiki.graf.Graf;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 *
 */
public  abstract class Criteri {

    public Criteri(double ponderacio) {
        this.ponderacio = ponderacio;
    }

    private double ponderacio;
    public abstract double getPes(NodeWiki n1, NodeWiki n2, Graf<NodeWiki> graf);

    public double getPonderacio() {
        return ponderacio;
    }
}
