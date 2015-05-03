package graf.grafgenerator.Criteris;

import domini.LevenshteinDistance;
import graf.GrafWikipedia;
import graf.NodeWiki;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 *
 */
public class CriteriNom extends Criteri{


    private final double maximGrauDiferencia;

    public CriteriNom(double ponderacio, double maximGrauDiferencia) {
        super(ponderacio);
        this.maximGrauDiferencia = maximGrauDiferencia;
    }

    @Override
    public double getPes(NodeWiki n1, NodeWiki n2, GrafWikipedia graf) {
        int cost = LevenshteinDistance.calculate(n1.getNom(), n2.getNom());

        int maximCost = (int) (Math.max(n1.getNom().length(), n2.getNom().length()) * maximGrauDiferencia);

        if (cost >  maximCost)
            return 0;
        else
            return maximCost - cost;
    }
}
