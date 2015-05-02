package graf.graftransform.Criteris;

import domini.NodeWiki;
import graf.graftransform.LevenshteinDistance;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 02/05/15.
 */
public class CriteriNom extends Criteri{


    private final double maximGrauDiferencia;

    public CriteriNom(double ponderacio, double maximGrauDiferencia) {
        super(ponderacio);
        this.maximGrauDiferencia = maximGrauDiferencia;
    }

    @Override
    public double getPes(NodeWiki n1, NodeWiki n2, Graf<NodeWiki> graf) {
        int cost = LevenshteinDistance.calculate(n1.getNom(), n2.getNom());

        int maximCost = (int) (Math.max(n1.getNom().length(), n2.getNom().length()) * maximGrauDiferencia);

        if (cost >  maximCost)
            return -1;
        else
            return cost;
    }
}
