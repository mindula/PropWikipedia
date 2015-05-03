package graf.grafgenerator.Criteris;

import graf.GrafWikipedia;
import graf.NodeWiki;

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
    public abstract double getPes(NodeWiki n1, NodeWiki n2, GrafWikipedia graf);

    public double getPonderacio() {
        return ponderacio;
    }
}
