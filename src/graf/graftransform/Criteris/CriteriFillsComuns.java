package graf.graftransform.Criteris;

import graf.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 *
 */
public class CriteriFillsComuns extends Criteri{
    public CriteriFillsComuns(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeWiki n1, NodeWiki n2, Graf<NodeWiki> graf) {
        double fillsComuns = 0;
        for(Arc<NodeWiki> a1 : graf.getNodesAdjacents(n1)){
            NodeWiki successor = Graf.getNodeOposat(n1, a1);
            if(graf.getArcEntre(successor, n2) != null){ // potser tira exepcio
                System.out.println("SUCCESSOR COMU!");
                fillsComuns++;
            }
        }

        return fillsComuns;
    }
}
