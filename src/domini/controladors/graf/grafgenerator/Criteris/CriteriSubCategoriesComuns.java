package domini.controladors.graf.grafgenerator.Criteris;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
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
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {


        double fillsComuns = 0;
        for(Arc<NodeCategoria> arc : graf.getNodesAdjacents(n1)){
            NodeCategoria fill = Graf.getNodeOposat(n1, arc);
            if(arc.getPes()< 0){
                Arc<NodeCategoria> arcn2fill = graf.getArcEntre(n2, fill);
                if(arcn2fill != null && arcn2fill.getPes() < 0) {
                    fillsComuns++;
                }
            }
        }

        return fillsComuns;
    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return Math.min(graf.getGrau(n1), graf.getGrau(n2));
    }

    public String toString() {
        return "SubcategoriesComunes " + getPonderacio();
    }
}
