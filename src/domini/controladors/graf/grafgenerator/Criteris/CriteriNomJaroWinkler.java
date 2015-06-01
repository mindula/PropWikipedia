package domini.controladors.graf.grafgenerator.Criteris;

import domini.JaroWinklerDistance;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Criteri per afinitat entre noms de categories
 */
public class CriteriNomJaroWinkler extends Criteri{


    public CriteriNomJaroWinkler(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        double jaro = JaroWinklerDistance.calculate(n1.getNom(), n2.getNom());
        return jaro < 0.1 ? 0:jaro/8 ;

    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return 1;
    }

    public String toString() {
        return "Nom " + getPonderacio();
    }
}
