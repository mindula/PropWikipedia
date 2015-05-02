package graf.graftransform.Criteris;

import domini.NodeWiki;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 30/04/15.
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
