package domini.controladors.graf.grafgenerator.Criteris;

import domini.LevenshteinDistance;
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
public class CriteriNomLevenshtein extends Criteri{


    public CriteriNomLevenshtein(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return LevenshteinDistance.calculate(n1.getNom(), n2.getNom());

    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return Math.max(n1.getNom().length(), n2.getNom().length());
    }

    public String toString() {
        return "Nom " + getPonderacio();
    }
}
