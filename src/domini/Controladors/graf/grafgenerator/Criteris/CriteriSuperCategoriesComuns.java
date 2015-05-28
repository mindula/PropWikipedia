package domini.controladors.graf.grafgenerator.Criteris;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.HashSet;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Criter per super categories comunes comunes entre categories
 */
public class CriteriSuperCategoriesComuns extends Criteri{

    public CriteriSuperCategoriesComuns(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        HashSet<Arc<NodeCategoria>> arcs1 = graf.getNodesAdjacents(n1);
        double paresComuns = 0;
        for(Arc<NodeCategoria> arc : arcs1){

                NodeCategoria pare = Graf.getNodeOposat(n1, arc);
                if(graf.existeixArcCC(pare, n2)){ // tot
                    paresComuns++;
                }

        }
        return paresComuns;
    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return Math.min(graf.getGrau(n1), graf.getGrau(n2));
    }

    public String toString() {
        return "SupercategoriesComunes " + getPonderacio();
    }

}
