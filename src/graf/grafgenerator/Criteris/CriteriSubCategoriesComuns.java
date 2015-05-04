package graf.grafgenerator.Criteris;

import graf.GrafWikipedia;
import graf.NodeWiki;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Criteri per subcategories comunes entre categories
 */
public class CriteriSubCategoriesComuns extends Criteri{
    public CriteriSubCategoriesComuns(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeWiki n1, NodeWiki n2, GrafWikipedia graf) {


        double fillsComuns = 0;
        for(Arc<NodeWiki> a1 : graf.getNodesAdjacents(n1)){
            NodeWiki successor = Graf.getNodeOposat(n1, a1);

            if(successor.esCategoria() && graf.existeixArc(n2, successor)){

                fillsComuns++;
            }
        }

        return fillsComuns;
    }
}
